SELECT * FROM MM_MOVIE;
SELECT * FROM MM_RENTAL;
SELECT * FROM MM_MEMBER;
SELECT * FROM MM_MOVIE_TYPE;
SELECT * FROM MM_PAY_TYPE;

/* returns category name from the category id */
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

/* returns payment name from the payment id */
create or replace FUNCTION payment_name(
        p_payment_id mm_pay_type.payment_methods_id%TYPE
    )
    RETURN VARCHAR2
IS
    lv_payment VARCHAR2(30);
BEGIN
    SELECT payment_methods
    INTO lv_payment
    FROM mm_pay_type
    WHERE payment_methods_id = p_payment_id;
    RETURN lv_payment;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'no payment method';
END;

/* returns movie name from the movie id */
create or replace FUNCTION movie_name(
        p_movie_id mm_movie.movie_id%TYPE
    )
    RETURN VARCHAR2
IS
    lv_movie VARCHAR2(30);
BEGIN
    SELECT movie_title
    INTO lv_movie
    FROM mm_movie
    WHERE movie_id = p_movie_id;
    RETURN lv_movie;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'movie not found';
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

/* auto increment mm_member */
create or replace TRIGGER member_increment
    BEFORE INSERT ON mm_member
    FOR EACH ROW
BEGIN
    SELECT mm_member_seq.NEXTVAL
    INTO :NEW.member_id
    FROM dual;
END;

/* auto increment mm_rental */
create or replace TRIGGER rental_increment
    BEFORE INSERT ON mm_rental
    FOR EACH ROW
BEGIN
    SELECT mm_rental_seq.NEXTVAL
    INTO :NEW.rental_id
    FROM dual;
END;

/* increment inventory of the movie when returned to store */
create or replace TRIGGER adjust_inventory_inc
    AFTER DELETE ON mm_rental
    FOR EACH ROW
BEGIN
    UPDATE mm_movie
    SET movie_qty = movie_qty + 1
    WHERE movie_id = :OLD.movie_id;
END;

/* decrement inventory of the movie when checked out */
create or replace TRIGGER adjust_inventory_dec
    BEFORE INSERT ON mm_rental
    FOR EACH ROW
DECLARE
    lv_movie_qty mm_movie.movie_qty%TYPE;
BEGIN
    SELECT movie_qty
    INTO lv_movie_qty
    FROM mm_movie
    WHERE movie_id = :NEW.movie_id;
    
    IF lv_movie_qty - 1 < 0 THEN
        raise_application_error(-20000,'Movie is out of stock!');
    ELSE
        UPDATE mm_movie SET movie_qty = lv_movie_qty - 1
        WHERE movie_id = :NEW.movie_id;
    END IF;
END;

/* update check-in date */
create or replace TRIGGER check_in_date
    BEFORE INSERT ON mm_rental
    FOR EACH ROW
BEGIN
    :NEW.checkin_date := :NEW.checkout_date + 14;
END;

/* delete all member and remove all rented movies */
create or replace PROCEDURE force_delete
( p_member_id mm_member.member_id%TYPE )
IS
BEGIN
    DELETE FROM mm_rental
    WHERE member_id = p_member_id;
    
    DELETE FROM mm_member
    WHERE member_id = p_member_id;
END;