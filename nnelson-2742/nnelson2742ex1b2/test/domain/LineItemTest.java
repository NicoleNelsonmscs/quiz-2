package domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.sound.sampled.Line;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class LineItemTest {

    LineItem lineItem1;


    @BeforeEach
    void setUp() {
        this.lineItem1 = new LineItem(1.0, "description1");
    }

    @Test
    @Order(0)
    void testConstructor() {
        assertAll(
                () -> assertEquals(10001, this.lineItem1.getLineItemId()),
                () -> assertEquals(1.0, this.lineItem1.getAmount()),
                () -> assertEquals("description1", this.lineItem1.getDescription())
                );
//        assertEquals(10001, this.lineItem1.getLineItemId());
//        assertEquals(1.0, this.lineItem1.getAmount());
//        assertEquals("description1", this.lineItem1.getDescription());
    }

    @Test
    @Order(1)
    void copy(){
        LineItem lineItem2 = new LineItem(lineItem1);
        LineItem lineItem3 = lineItem1.copy();
        assertEquals(lineItem3.getLineItemId(), lineItem2.getLineItemId());
        assertEquals(lineItem3.getAmount(), lineItem2.getAmount());
        assertEquals(lineItem3.getDescription(), lineItem2.getDescription());
        //assertNotEquals(lineItem3, lineItem2);
        assertFalse(lineItem3 == lineItem2);
    }

//    @Test
//    @Order(1)
//    void copyConstructor() {
//        LineItem lineItem2 = new LineItem(lineItem1);
//        assertEquals(this.lineItem1.getLineItemId(), lineItem2.getLineItemId());
//        assertEquals(this.lineItem1.getAmount(), lineItem2.getAmount());
//        assertEquals(this.lineItem1.getDescription(), lineItem2.getDescription());
//        //assertNotEquals(this.lineItem1, lineItem2);
//        assertFalse(lineItem1 == lineItem2);
//    }
//
//    @Test
//    @Order(2)
//    void copy() {
//        LineItem lineItem2 = lineItem1.copy();
//        assertEquals(this.lineItem1.getLineItemId(), lineItem2.getLineItemId());
//        assertEquals(this.lineItem1.getAmount(), lineItem2.getAmount());
//        assertEquals(this.lineItem1.getDescription(), lineItem2.getDescription());
//        //assertNotEquals(this.lineItem1, lineItem2);
//        assertFalse(lineItem1 == lineItem2);
//    }

    @Test
    @Order(2)
    void equals() {
        LineItem lineItem2 = this.lineItem1.copy();
        assertTrue(lineItem1.equals(lineItem2));
    }
}
