/*
 * @Author: Harry 
 * @Date: 2020-01-19 15:47:33 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-20 16:15:44
 */

import baseSql from './baseSql'
import User from "../model/User"

const table = "user";
const pkey = "userName";

 class UserDao{
     constructor(){
     }

     selectUser(object){
        return baseSql.select(User,table,object,pkey);
     }
     save(object){
         baseSql.save(User,table,object,pkey);
     }
     update(object){
         baseSql.update(User,table,object,pkey);
     }
     insert(object){
         baseSql.insert(User,table,object,pkey);
     }
     delete(object){
         baseSql.delete(User,table,object,pkey);
     }
     opBySql(sql){
        baseSql.opBySql(sql);
     }

 }

 export default UserDao;