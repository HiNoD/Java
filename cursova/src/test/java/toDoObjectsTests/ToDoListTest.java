package toDoObjectsTests;

import com.company.toDoObjects.Deal;
import com.company.toDoObjects.IToDoList;
import com.company.toDoObjects.ToDoList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.company.status.Completed;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ToDoListTest {
    IToDoList toDoList;
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Before
    public void init() {
        toDoList = new ToDoList("Test");
        Deal deal = new Deal("Deal");
        toDoList.addDeal(deal);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }


    @Test
    public void canCreateUndefinedToDoList() {
        ToDoList toDoList = new ToDoList();
        assertEquals(toDoList.getName(), "Undefined");
        assertTrue(toDoList.getDeals().size() == 0);
    }

    @Test
    public void canCreateDeal() {
        toDoList.createDeal("New deal");
        assertTrue(toDoList.getDeals().size() == 2);
    }

    @Test
    public void canDeleteDeal() {
        toDoList.deleteDeal(1);
        assertEquals(toDoList.toString(), toDoList.getName() + "\nList is empty");
        assertTrue(toDoList.getDeals().size() == 0);
    }

    @Test
    public void canChangeStatus() {
        toDoList.changeStatus(1 , new Completed());
    }

    @Test
    public void cantOpenList() {
        assertNull(toDoList.openList(1));
    }

    @Test
    public void cantCreateList() {
        toDoList.createList("Listname");
        assertEquals("You can't create list here" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void cantDeleteList() {
        toDoList.deleteList(2);
        assertEquals("You can't delete list here" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void canSetToDoListName() {
        toDoList.setName("New name");
        assertEquals(toDoList.getName(), "New name");
    }
    @Test
    public void cantGetToDoListStatus() {
        assertNull(toDoList.getStatus());
    }
    @Test
    public void canRenameDeal() {
        toDoList.renameTo(1, "new name");
    }
}