package catcafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/** Tests for {@link CatCafe}. */
class CatCafeTest {

    @Test
    void returns_zero_cats_for_a_new_cafe() {
        // given
        var cafe = new CatCafe();

        // when
        var catCount = cafe.getCatCount();

        // then
        assertEquals(0, catCount);
    }

    @Test
    void increments_cat_count_after_adding_one_cat() {
        // given
        var cafe = new CatCafe();
        var cat = new FelineOverLord("Morticia", 3);

        // when
        cafe.addCat(cat);
        var catCount = cafe.getCatCount();

        // then
        assertEquals(1, catCount);
    }

    @Test
    void increments_cat_count_after_adding_multiple_cats() {
        // given
        var cafe = new CatCafe();

        // when
        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(new FelineOverLord("Morticia", 3));
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));
        var catCount = cafe.getCatCount();

        // then
        assertEquals(3, catCount);
    }

    @Test
    void throws_null_pointer_exception_when_adding_null_cat() {
        // given
        var cafe = new CatCafe();

        // when + then
        assertThrows(NullPointerException.class, () -> cafe.addCat(null));
    }

    @Test
    void returns_null_for_name_lookup_in_an_empty_cafe() {
        // given
        var cafe = new CatCafe();

        // when
        var cat = cafe.getCatByName("Morticia");

        // then
        assertNull(cat);
    }

    @Test
    void returns_null_for_name_lookup_with_null_name() {
        // given
        var cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Morticia", 3));

        // when
        var cat = cafe.getCatByName(null);

        // then
        assertNull(cat);
    }

    @Test
    void returns_matching_cat_for_name_lookup() {
        // given
        var cafe = new CatCafe();
        var morticia = new FelineOverLord("Morticia", 3);
        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(morticia);
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));

        // when
        var cat = cafe.getCatByName("Morticia");

        // then
        assertSame(morticia, cat);
    }

    @Test
    void returns_null_for_unknown_name_lookup() {
        // given
        var cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(new FelineOverLord("Morticia", 3));

        // when
        var cat = cafe.getCatByName("Gwenapurr Esmeralda");

        // then
        assertNull(cat);
    }

    @Test
    void returns_null_for_weight_lookup_with_negative_minimum() {
        // given
        var cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Morticia", 3));

        // when
        var cat = cafe.getCatByWeight(-1, 4);

        // then
        assertNull(cat);
    }

    @Test
    void returns_null_for_weight_lookup_when_maximum_is_smaller_than_minimum() {
        // given
        var cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Morticia", 3));

        // when
        var cat = cafe.getCatByWeight(4, 3);

        // then
        assertNull(cat);
    }
}
