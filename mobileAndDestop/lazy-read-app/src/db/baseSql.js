/*
 * @Author: Harry 
 * @Date: 2020-01-19 15:48:46 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-20 16:05:25
 */

import _this from "@/main"
import ReflectHelper from "@/model/ReflectHelper";

var that = _this._this;
var reflectHelper = new ReflectHelper();

 export default {
     save(T,table,object,pkey){
        let data = this.select(T,table,object,pkey);
        let _that = this;
        setTimeout(function(){
            console.log(data);
            if(data.length > 0){
                console.log("update")
                _that.update(T,table,object,pkey);
            }
            else{
                console.log("insert");
                _that.insert(T,table,object,pkey);
            }
        },2000);
     },
     insert(T,table,object,pkey){
        let sql = "insert into " + table + " (";

        //获取t对象的属性
        let tPropertyArr = getObjectProperty(T);

        let counter = 0;
        let sql2 = (" values (")
        for(let i = 0;i < tPropertyArr.length;i++){
            let property = Reflect.get(object,tPropertyArr[i]);
            if(!(property === undefined || property === null)){
                if(counter != 0){
                    sql += ",";
                    sql2 += ",";
                }
                sql += tPropertyArr[i];
                sql2 += judgeType(property);
                counter++;
            }
        }

        sql += ")";
        sql2 += ")";
        sql += sql2;

        opDb(sql);
        return sql;
     },
     update(T,table,object,pkey){
        let sql = "update " + table + " set ";
        
        //获取t对象的属性
        let tPropertyArr = getObjectProperty(T);
        
        let counter = 0;
        for(let i = 0;i < tPropertyArr.length;i++){
            let property = Reflect.get(object,tPropertyArr[i]);
            if(!(property === undefined || property === null)){
                if(counter != 0){
                    sql += ",";
                }
                sql += tPropertyArr[i] + "=";
                sql += judgeType(property);
                counter++;
            }
        }

        sql += " where " + pkey + "=" + judgeType(Reflect.get(object,pkey));
        opDb(sql);
        return sql;
     },
     delete(table,object,pkey){
        let sql = "delete from " + table + " where " + pkey + "="+ judgeType(Reflect.get(object,pkey));
        opDb(sql);
        return sql;
     },
     select(T,table,object,pkey){
        let sql = "select * from " + table + " where " + pkey + "=" + judgeType(Reflect.get(object,pkey));
        let data = [];
        that.$db.transaction(function (tx) {
            tx.executeSql(sql,
                [],
              function (tx, results) {
                  if (results.rows && results.rows.length) {
                      for (let i = 0; i < results.rows.length; i++) {
                          data.push(reflectHelper.getOwnObject(T,results.rows.item(i)));
                      } 
                  }
                  if ( typeof(callback) == 'function' ) callback(data);
            }, 
            function (tx, error) {
                console.log(error);
            });
        });

        return data;
     },
     opBySql(sql){
        opDb(sql);
     }
 }

 function opDb(sql){
    that.$db.transaction(function(tx){
        tx.executeSql(sql);
    })
 }

 function judgeType(val){
     if(typeof(val) === 'number'){
         return val;
     }
     else{
         return "'" + val + "'";
     }
 }

 function getObjectProperty(T){
    let t = new T();//先创建class
        
    //获取t对象的属性
    let tPropertyArr = Reflect.ownKeys(t);

    return tPropertyArr;
 }