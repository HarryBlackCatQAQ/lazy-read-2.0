package com.hhr.lazyread.model;

import java.util.ArrayList;
import java.util.List;

public class BookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public BookExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    public void setForUpdate(Boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public Boolean getForUpdate() {
        return forUpdate;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("book.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("book.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("book.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("book.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("book.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("book.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("book.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("book.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("book.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("book.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("book.`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("book.`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("book.`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("book.`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("book.`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("book.`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("book.`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("book.`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("book.`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("book.`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("book.`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("book.`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("book.`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("book.`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("book.imageUrl is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("book.imageUrl is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("book.imageUrl =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("book.imageUrl <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("book.imageUrl >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("book.imageUrl >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("book.imageUrl <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("book.imageUrl <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("book.imageUrl like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("book.imageUrl not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("book.imageUrl in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("book.imageUrl not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("book.imageUrl between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("book.imageUrl not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("book.author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("book.author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("book.author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("book.author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("book.author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("book.author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("book.author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("book.author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("book.author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("book.author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("book.author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("book.author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("book.author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("book.author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andWebTypeIsNull() {
            addCriterion("book.webType is null");
            return (Criteria) this;
        }

        public Criteria andWebTypeIsNotNull() {
            addCriterion("book.webType is not null");
            return (Criteria) this;
        }

        public Criteria andWebTypeEqualTo(String value) {
            addCriterion("book.webType =", value, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeNotEqualTo(String value) {
            addCriterion("book.webType <>", value, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeGreaterThan(String value) {
            addCriterion("book.webType >", value, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeGreaterThanOrEqualTo(String value) {
            addCriterion("book.webType >=", value, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeLessThan(String value) {
            addCriterion("book.webType <", value, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeLessThanOrEqualTo(String value) {
            addCriterion("book.webType <=", value, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeLike(String value) {
            addCriterion("book.webType like", value, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeNotLike(String value) {
            addCriterion("book.webType not like", value, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeIn(List<String> values) {
            addCriterion("book.webType in", values, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeNotIn(List<String> values) {
            addCriterion("book.webType not in", values, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeBetween(String value1, String value2) {
            addCriterion("book.webType between", value1, value2, "webType");
            return (Criteria) this;
        }

        public Criteria andWebTypeNotBetween(String value1, String value2) {
            addCriterion("book.webType not between", value1, value2, "webType");
            return (Criteria) this;
        }

        public Criteria andBookUrlIsNull() {
            addCriterion("book.bookUrl is null");
            return (Criteria) this;
        }

        public Criteria andBookUrlIsNotNull() {
            addCriterion("book.bookUrl is not null");
            return (Criteria) this;
        }

        public Criteria andBookUrlEqualTo(String value) {
            addCriterion("book.bookUrl =", value, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlNotEqualTo(String value) {
            addCriterion("book.bookUrl <>", value, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlGreaterThan(String value) {
            addCriterion("book.bookUrl >", value, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlGreaterThanOrEqualTo(String value) {
            addCriterion("book.bookUrl >=", value, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlLessThan(String value) {
            addCriterion("book.bookUrl <", value, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlLessThanOrEqualTo(String value) {
            addCriterion("book.bookUrl <=", value, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlLike(String value) {
            addCriterion("book.bookUrl like", value, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlNotLike(String value) {
            addCriterion("book.bookUrl not like", value, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlIn(List<String> values) {
            addCriterion("book.bookUrl in", values, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlNotIn(List<String> values) {
            addCriterion("book.bookUrl not in", values, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlBetween(String value1, String value2) {
            addCriterion("book.bookUrl between", value1, value2, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andBookUrlNotBetween(String value1, String value2) {
            addCriterion("book.bookUrl not between", value1, value2, "bookUrl");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("book.userId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("book.userId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("book.userId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("book.userId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("book.userId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book.userId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("book.userId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("book.userId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("book.userId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("book.userId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("book.userId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book.userId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andLatestChapterIsNull() {
            addCriterion("book.latestChapter is null");
            return (Criteria) this;
        }

        public Criteria andLatestChapterIsNotNull() {
            addCriterion("book.latestChapter is not null");
            return (Criteria) this;
        }

        public Criteria andLatestChapterEqualTo(Integer value) {
            addCriterion("book.latestChapter =", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNotEqualTo(Integer value) {
            addCriterion("book.latestChapter <>", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterGreaterThan(Integer value) {
            addCriterion("book.latestChapter >", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterGreaterThanOrEqualTo(Integer value) {
            addCriterion("book.latestChapter >=", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterLessThan(Integer value) {
            addCriterion("book.latestChapter <", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterLessThanOrEqualTo(Integer value) {
            addCriterion("book.latestChapter <=", value, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterIn(List<Integer> values) {
            addCriterion("book.latestChapter in", values, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNotIn(List<Integer> values) {
            addCriterion("book.latestChapter not in", values, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterBetween(Integer value1, Integer value2) {
            addCriterion("book.latestChapter between", value1, value2, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNotBetween(Integer value1, Integer value2) {
            addCriterion("book.latestChapter not between", value1, value2, "latestChapter");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameIsNull() {
            addCriterion("book.latestChapterName is null");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameIsNotNull() {
            addCriterion("book.latestChapterName is not null");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameEqualTo(String value) {
            addCriterion("book.latestChapterName =", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameNotEqualTo(String value) {
            addCriterion("book.latestChapterName <>", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameGreaterThan(String value) {
            addCriterion("book.latestChapterName >", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameGreaterThanOrEqualTo(String value) {
            addCriterion("book.latestChapterName >=", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameLessThan(String value) {
            addCriterion("book.latestChapterName <", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameLessThanOrEqualTo(String value) {
            addCriterion("book.latestChapterName <=", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameLike(String value) {
            addCriterion("book.latestChapterName like", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameNotLike(String value) {
            addCriterion("book.latestChapterName not like", value, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameIn(List<String> values) {
            addCriterion("book.latestChapterName in", values, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameNotIn(List<String> values) {
            addCriterion("book.latestChapterName not in", values, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameBetween(String value1, String value2) {
            addCriterion("book.latestChapterName between", value1, value2, "latestChapterName");
            return (Criteria) this;
        }

        public Criteria andLatestChapterNameNotBetween(String value1, String value2) {
            addCriterion("book.latestChapterName not between", value1, value2, "latestChapterName");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}