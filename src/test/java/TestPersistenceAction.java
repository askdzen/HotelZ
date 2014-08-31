import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceAction.CustomerPersistenceAction;
import com.epam.ad.dao.h2.DaoFactory;

/**
 * Created by Askar on 30.08.2014.
 */
public class TestPersistenceAction {
    public static void main(String[] args) throws DaoException {
        DaoManager daoManager= DaoFactory.getInstance().createDaoManager();
        CustomerPersistenceAction persistenceAction=new CustomerPersistenceAction(daoManager);
        persistenceAction.doPersistenceAction(daoManager);
    }
}
