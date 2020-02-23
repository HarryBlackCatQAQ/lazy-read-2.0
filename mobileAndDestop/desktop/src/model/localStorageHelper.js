/*
 * @Author: Harry 
 * @Date: 2019-10-22 15:47:42 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-19 15:33:03
 */
 

class Storage {
    constructor(props) {
        this.props = props || {}
        this.source = this.props.source || window.localStorage
        this.initRun();

        this.second = 1000;
        this.minute = 60 * this.second;
        this.hour = this.minute * 60;
        this.day = this.hour * 24;
        this.month = this.hour * 30;//默认30天一个月
        this.year = this.month * 12;
    }
    initRun(){
        /*
        * set 存储方法
        * @ param {String}     key 键
        * @ param {String}     value 值，存储的值可能是数组/对象，不能直接存储，需要转换 JSON.stringify
        * @ param {String}     expired 过期时间，以分钟为单位
        */
        const reg = new RegExp("__expires__");
        let data = this.source;
        let list = Object.keys(data);
        if(list.length > 0){
            list.map((key,v)=>{
                if( !reg.test(key )){
                    let now = Date.now();
                    let expires = data[`${key}__expires__`]||Date.now+1;
                    if (now >= expires ) {
                        this.remove(key);
                    };
                };
                return key;
            });
        };
    }

    set(key, value, expired) {
        /*
        * set 存储方法
        * @ param {String}     key 键
        * @ param {String}     value 值，
        * @ param {String}     expired 过期时间，以毫秒为单位，非必须
        */
        let source = this.source;
        source[key] = JSON.stringify(value);
        if (expired){
            source[`${key}__expires__`] = Date.now() + expired
        };
        return value;
    }

    get(key) {
        /*
        * get 获取方法
        * @ param {String}     key 键
        * @ param {String}     expired 存储时为非必须字段，所以有可能取不到，默认为 Date.now+1
        */
        const source = this.source,
        expired = source[`${key}__expires__`]||Date.now+1;
        const now = Date.now();
    
        if ( now >= expired ) {
            this.remove(key);
            return;
        }
        const value = source[key] ? JSON.parse(source[key]) : source[key];
        return value;
    }

    remove(key) {
        const data = this.source,
            value = data[key];
        delete data[key];
        delete data[`${key}__expires__`];
        return value;
    }

}


export default Storage