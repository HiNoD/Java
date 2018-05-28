package workWithFilesTests;

import com.company.workWithFiles.DataWorker;
import org.junit.Before;
import org.junit.Test;
import com.company.status.Completed;
import com.company.status.IStatus;
import com.company.toDoObjects.IMainList;
import com.company.toDoObjects.MainList;

import java.io.File;

import static org.junit.Assert.*;

public class DataWorkerTest {
    DataWorker dataWorker;
    IMainList mainList;
    File dir;

    @Before
    public void init() {
        dataWorker = new DataWorker();
        mainList = new MainList();
        dir = new File("src/main/resources/test");
    }

    @Test
    public void canReadAndWriteListToFiles() {
        try {
            mainList.createList("New list");
            mainList.getLists().get(0).createDeal("deal");
            dataWorker.convertDataToFiles(mainList, dir);
            IMainList testList = dataWorker.convertDataFromFiles(dir);
            assertTrue(testList.getLists().size() > 0);
            File outFile = new File(dir + "/" + mainList.getLists().get(0).getName() +".txt");
            assertTrue(outFile.exists());
            outFile.deleteOnExit();
        } catch (Exception ex) {
        }
    }

    @Test
    public void cantReadFilesIfDirectoryEmpty() {
        File emptyDir = new File(dir.getPath() + "/empty");
        if (!emptyDir.exists()) {
            emptyDir.mkdir();
        }
        try {
           IMainList testList = dataWorker.convertDataFromFiles(emptyDir);
           assertEquals(testList, mainList);
        } catch (Exception ex) {

        }
        emptyDir.deleteOnExit();
    }
}
