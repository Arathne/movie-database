SELECT * FROM MM_MOVIE;
DESC MM_MOVIE;
SELECT * FROM MM_RENTAL;
SELECT * FROM MM_MEMBER;
SELECT * FROM MM_MOVIE_TYPE;
SELECT * FROM MM_PAY_TYPE;

/* returns category name from the given id */
create or replace FUNCTION category_name(
        p_category_id mm_movie_type.movie_cat_id%TYPE
    )
    RETURN VARCHAR2
IS
    lv_category VARCHAR2(13);
BEGIN
    SELECT movie_category
    INTO lv_category
    FROM mm_movie_type
    WHERE movie_cat_id = p_category_id;
    RETURN lv_category;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'no category';
END;

/* auto increment mm_movie */
/* mm_movie_seq */
create or replace TRIGGER movie_increment
    BEFORE INSERT ON mm_movie
    FOR EACH ROW
BEGIN
    SELECT mm_movie_seq.NEXTVAL
    INTO :NEW.movie_id
    FROM dual;
END;

INSERT INTO MM_MOVIE( movie_title, movie_cat_id, movie_value, movie_qty ) VALUES( 'E.T Phone Home', 10, 86, 3 );

COMMIT;




