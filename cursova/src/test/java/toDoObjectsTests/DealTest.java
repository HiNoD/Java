package toDoObjectsTests;

import com.company.toDoObjects.Deal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.company.status.IStatus;
import com.company.status.InProcess;
import com.company.status.Status;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DealTest {
    Deal deal;
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        deal  = new Deal("Main deal");
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void canCreateUnderfinedDeal() {
        Deal undefinedDeal = new Deal();
        assertEquals(undefinedDeal.getName(), "Undefined");
        assertEquals(undefinedDeal.getStatus().toString(), new InProcess().toString());
    }

    @Test
    public void cantPerformToDoListFunctions() {
        assertNull(deal.openList(1));

    }

    @Test
    public void cantCreateList() {
        deal.createList("name");
        assertEquals("You can't create list here" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void cantCreateDealInsideThem() {
        deal.createDeal("Deal");
        assertEquals("You can't create deal here" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void cantDeleteDeal() {
        deal.deleteDeal(1);
        assertEquals("You can't delete deal here" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void cantDeleteList() {
        deal.deleteList(4);
        assertEquals("You can't delete list here" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void canRenameDeal() {
        deal.setName("New name");
        assertEquals(deal.getName(), "New name");
    }
}