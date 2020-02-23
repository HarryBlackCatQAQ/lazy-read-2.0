/*
 * @Author: Harry 
 * @Date: 2020-01-19 16:39:35 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-20 16:55:20
 */

import Vue from 'vue'

const WebSql = function () {
    if (window.openDatabase) {
        //openDatabase('数据库名称','版本','数据库描述','数据库大小')
        var db = openDatabase('lazyreadDB', '1.0', 'lazyread', 100 * 1024 * 1024);

        Vue.prototype.$db=db;
        
        if (!db) {
            console.log("数据库创建失败！");
        } else {
            console.log('本地数据库创建成功！');

            db.transaction(function(tx){
                // tx.executeSql('CREATE TABLE IF NOT EXISTS user(id,userName,nickName,password,email,phone)');
                // tx.executeSql("update user set id=6,userName='root',nickName='harry',password='$2a$10$hNPuZRtkAX7Jxn1jbkEP7upNDGhItLeP9rfnltodvbObAE9MChvge',email='123@lazy.com',phone='123456' where userName='root'")
                // tx.executeSql("update user set userName='root',password='123456',id=27 where userName='root'")
                // tx.executeSql("insert into user (id,userName,nickName,password,email,phone) values (6,'root','harry','$2a$10$hNPuZRtkAX7Jxn1jbkEP7upNDGhItLeP9rfnltodvbObAE9MChvge','123@lazy.com','123456')")
                // tx.executeSql("insert into user (userName,password) values ('root','123456')");
                // tx.executeSql("delete from user");
                // tx.executeSql("delete from user where userName='root'");
                // tx.executeSql('drop table user');
                // tx.executeSql('select * from user',[],function(tx,result){
				// 	console.log(result.rows[0]);
					
				// })
            });
        }
    } else {
        console.log('不支持本地存储！');
    }
}

export default WebSql;