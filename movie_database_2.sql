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
    lv_category VARCHAR2(13) := 'uncategorized';
BEGIN
    SELECT movie_category
    INTO lv_category
    FROM mm_movie_type
    WHERE movie_cat_id = p_category_id;
    RETURN lv_category;
END;

/* auto increment mm_movie */
create SEQUENCE mm_movie_seq;

create or replace TRIGGER movie_increment
    BEFORE INSERT ON MM_MOVIE
    FOR EACH ROW
BEGIN

END;








