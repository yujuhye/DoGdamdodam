CREATE DATABASE DB_DOGDAM;

USE DB_DOGDAM;

CREATE TABLE ADMIN(
	A_NO		INT			AUTO_INCREMENT,
    A_ID		VARCHAR(20) 	NOT NULL,
    A_NAME		VARCHAR(30) 	NOT NULL,
    A_PW		VARCHAR(20) 	NOT NULL,
    A_POSITION	VARCHAR(20) 	NOT NULL,
    A_PART		VARCHAR(20) 	NOT NULL,
    A_PHONE		VARCHAR(30) 	NOT NULL,
    A_MAIL		VARCHAR(40) 	NOT NULL,
    A_APPROVAL	TINYINT			DEFAULT 0 	NOT NULL,
    A_ARV_DATE	TIMESTAMP,
    A_REG_DATE	TIMESTAMP,
    A_MOD_DATE	TIMESTAMP,
    PRIMARY KEY(A_NO)
);

CREATE TABLE USER(
	U_NO 		INT				AUTO_INCREMENT,
    U_ID		VARCHAR(20)		NOT NULL,
    U_NAME		VARCHAR(30)		NOT NULL,
    U_PW		VARCHAR(20)		NOT NULL,
    U_NICKNAME	VARCHAR(20)		NOT NULL,
	U_MAIL		VARCHAR(40)		NOT NULL,
    U_PHONE		VARCHAR(30)		NOT NULL,
    U_GRADE		INT				DEFAULT 0	NOT NULL,
    U_REG_DATE	TIMESTAMP,
    U_MOD_DATE	TIMESTAMP,
    PRIMARY KEY(U_NO)
);

CREATE TABLE USER_PET_INFO(
	P_NO		INT			AUTO_INCREMENT,
    U_ID		VARCHAR(20)	NOT NULL,
    P_SPECIES	VARCHAR(30)	NOT NULL,
    P_AGE		INT			NOT NULL,
    P_REG_DATE	TIMESTAMP,
    P_MOD_DATE	TIMESTAMP,
    PRIMARY KEY(P_NO)
);

CREATE TABLE SHOPPING_BASKET(
	SB_NO		INT			AUTO_INCREMENT,
    U_ID		VARCHAR(20)	NOT NULL,
    G_NO		INT			NOT NULL,
    SB_GROUP_NO	INT,
    SB_REG_DATE TIMESTAMP,
    PRIMARY KEY(SB_NO)
);


CREATE TABLE BOOK_MARK(
	BM_NO		INT			AUTO_INCREMENT,
    U_ID		VARCHAR(20)	NOT NULL,
    G_NO		INT			NOT NULL,
    BM_REG_DATE TIMESTAMP,
    PRIMARY KEY(BM_NO)
);

CREATE TABLE WEB_SERVICE_CENTER(
	WS_NO			INT				AUTO_INCREMENT,
    A_ID			VARCHAR(20) 	NOT NULL,
    U_ID			VARCHAR(20)		NOT NULL,
    WS_INQURIY		VARCHAR(600)	NOT NULL,
    WS_ANSWER		VARCHAR(1000)	NOT NULL,
    WS_COMPLETE		TINYINT			DEFAULT 0	NOT NULL,
    WS_REG_DATE 	TIMESTAMP,
    WS_MOD_DATE 	TIMESTAMP,
    WS_ANSWER_DATE	TIMESTAMP,
    PRIMARY KEY(WS_NO)
);

CREATE TABLE GOODS(
	G_NO				INT				AUTO_INCREMENT,
    G_NAME				VARCHAR(100)	NOT NULL,
    G_THUMBNAIL_NAME	VARCHAR(50)		NOT NULL,
    G_DETAIL_CONTENT	VARCHAR(50),
    C_NO				INT				NOT NULL,
    G_QUANTITY			INT				DEFAULT 0	 NOT NULL,
    G_EXPLANATION		VARCHAR(200),				
    G_PRICE				INT				NOT NULL,
    G_APPROVAL			TINYINT			DEFAULT 0	 NOT NULL,
    G_REG_DATE 			TIMESTAMP,
    G_MOD_DATE 			TIMESTAMP,
    PRIMARY KEY(G_NO)
);

CREATE TABLE GOODS_QA(
	GQA_NO			INT				AUTO_INCREMENT,
    G_NO			INT				NOT NULL,
    A_ID			VARCHAR(20) 	NOT NULL,
    U_ID			VARCHAR(20)		NOT NULL,
    GQA_INQURIY		VARCHAR(600)	NOT NULL,
    GQA_ANSWER		VARCHAR(1000)	NOT NULL,
    GQA_COMPLETE	TINYINT			DEFAULT 0	NOT NULL,
    GQA_REG_DATE 	TIMESTAMP,
    GQA_MOD_DATE 	TIMESTAMP,
    GQA_ANSWER_DATE TIMESTAMP,
    PRIMARY KEY(GQA_NO)
);

CREATE TABLE DELETE_MEMBER(
	D_M_ID	VARCHAR(20)		PRIMARY KEY
);

CREATE TABLE DONATION(
	D_NO		INT			 AUTO_INCREMENT,
    S_NAME		VARCHAR(100) NOT NULL,
    U_ID		VARCHAR(20)  NOT NULL,
    D_DONATION	INT			 NOT NULL,
    D_REG_DATE	TIMESTAMP,
    D_MOD_DATE	TIMESTAMP,
    PRIMARY KEY(D_NO)
);


CREATE TABLE REVIEW(
	R_NO				INT				AUTO_INCREMENT,
    G_NO				INT				NOT NULL,
    U_ID				VARCHAR(20)		NOT NULL,
    R_TEXT				VARCHAR(200)	NOT NULL,
    R_THUMBNAIL_NAME	VARCHAR(50)		NOT NULL,
    R_RATING			FLOAT			NOT NULL,
    R_REG_DATE			TIMESTAMP,
    R_MOD_DATE			TIMESTAMP,
    PRIMARY KEY(R_NO)
);

CREATE TABLE CATEFORY(
	C_NO			INT			AUTO_INCREMENT,
    C_PARENTS_NO	INT			NOT NULL,
    C_NAME			VARCHAR(20)	NOT NULL,
    C_STEP			INT,
    C_REG_DATE		TIMESTAMP,
    C_MOD_DATE		TIMESTAMP,
    PRIMARY KEY(C_NO)
);

CREATE TABLE DELIVERY_ADDR(
	DA_NO			INT			AUTO_INCREMENT,
    U_ID			VARCHAR(20)	NOT NULL,
    DA_ANOTHER_ADDR	VARCHAR(100),
    PRIMARY KEY(DA_NO)
);

CREATE TABLE SALES(
	S_NAME				VARCHAR(100),
	G_NO				INT				NOT NULL,
    U_ID				VARCHAR(20)		NOT NULL,
    SB_GROUP_NO			INT,
    S_RETURN_CONFIG		TINYINT			DEFAULT 1	NOT NULL,
    S_REG_DATE			TIMESTAMP,
    S_MOD_DATE			TIMESTAMP,
    PRIMARY KEY(S_NAME)
);



SELECT * FROM ADMIN;
SELECT * FROM USER;
SELECT * FROM USER_PET_INFO;
SELECT * FROM SHOPPING_BASKET;
SELECT * FROM BOOK_MARK;
SELECT * FROM WEB_SERVICE_CENTER;
SELECT * FROM GOODS;
SELECT * FROM GOODS_QA;
SELECT * FROM DELETE_MEMBER;
SELECT * FROM DONATION;
SELECT * FROM REVIEW;
SELECT * FROM CATEFORY;
SELECT * FROM DELIVERY_ADDR;
SELECT * FROM SALES;