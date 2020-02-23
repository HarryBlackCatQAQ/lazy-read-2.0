/*
 * @Author: Harry 
 * @Date: 2020-01-01 17:34:59 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-20 15:28:01
 */

//反射帮助类
 class ReflectHelper{
    constructor(){}

    /**
     * 
     * @param {*} T 传入的class
     * @param {*} Object 实体类
     */
    getOwnObject(T,object){
        let t = new T();
        //获取t对象的属性
        let tPropertyArr = this.getClassProperty(T);
        
        //遍历属性
        for(let i = 0;i < tPropertyArr.length;i++){
            let property = null;
            //反射判断 object中有没有T 对象中的属性
            if(Reflect.has(object,tPropertyArr[i])){
                //有的话获取其属性
                property = Reflect.get(object,tPropertyArr[i]);
                // //手动判断undefined 为null
                // if(property === undefined){
                //     property = null;
                // }
            }
            
            //反射 给t对象复制
            Reflect.set(t,tPropertyArr[i],property);
        }

        return t;
    }
    getClassProperty(T){
        let t = new T();//先创建class
        
        //获取t对象的属性
        let tPropertyArr = Reflect.ownKeys(t);

        return tPropertyArr;
    }
 }

 export default ReflectHelper;