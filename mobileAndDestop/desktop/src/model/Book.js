/*
 * @Author: Harry 
 * @Date: 2020-01-01 17:02:14 
 * @Last Modified by: Harry-mac
 * @Last Modified time: 2020-01-01 17:30:11
 */


class Book {
    constructor(id, name, imageUrl, author, webType, bookUrl, userId, latestChapter, latestChapterName) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.author = author;
        this.webType = webType;
        this.bookUrl = bookUrl;
        this.userId = userId;
        this.latestChapter = latestChapter;
        this.latestChapterName = latestChapterName;
    }

    
}

export default Book;