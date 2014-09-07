import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.RoomDao;
import com.epam.ad.entity.Room;

/**
 * Created by Askar on 01.09.2014.
 */
public class TestRoomCreate {
    public static void main(String[] args) {
        DaoFactory daoFactory=new DaoFactory();
        RoomDao roomDao = null;
        try {
            roomDao =  daoFactory.createDaoManager().getRoomDao();
            roomDao.createRecord("140","Non Ac","Double","1500");
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
