import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.GenericDao;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Askar on 30.07.2014.
 */
public class TestBookingDAO {

    public static final Logger LOGGER = Logger.getLogger(TestBookingDAO.class);
    public static void main(String[] args) throws SQLException, DaoException, InterruptedException, ClassNotFoundException {
        Class bookingTableClass=BookingTable.class;
        DaoFactory daoFactory=new DaoFactory();
       BookingTableDao bookingTableDao=new BookingTableDao(daoFactory.getContext());
       GenericDao genericDao= daoFactory.getDao(bookingTableClass);

 //getALL
//        try {
           List<BookingTable>bookingTables= genericDao.getAll();
            for (BookingTable bookingTable : bookingTables) {
                LOGGER.info(bookingTable.getId()+" "+bookingTable.getRoomNo()+" "+bookingTable.getDateFrom()+" "+bookingTable.getDayCount());
            }
//Update
            Date date= bookingTableDao.getByPK(148).getDateFrom();
            LOGGER.info(date);

            BookingTable bookingTable2=new BookingTable();

            bookingTable2.setDateFrom(date);
            bookingTable2.setDateTo(date);
            bookingTable2.setDayCount(2);
            bookingTable2.setRoomNo(9);
        bookingTable2.setUserId(1);
        bookingTable2.isConfirmed();
        bookingTable2.setConfirm(BookingTable.Confirm.CONFIRM);
            bookingTable2.setId(172);

           bookingTableDao.update(bookingTable2);

            System.out.println();

 //create
      /*  */
            //создаем пустую строку

//            BookingTable newBookingtable=new BookingTable();
//           newBookingtable.setDateFrom(new Date(114,7,7));
//           newBookingtable.setDateTo(new Date(114,8,9));
//          newBookingtable.setDayCount(5);
//          newBookingtable.setRoomNo(9);
//            newBookingtable.setId(bookingTableDao.create().getId());
//
//           bookingTableDao.update(newBookingtable);

 //delete
 //genericDao.delete(bookingTableDao.getByPK(29));

            List<BookingTable>bookingTables2= bookingTableDao.getAll();
            for (BookingTable bookingTable1 : bookingTables2) {
                LOGGER.info(bookingTable1.getId()+" "+bookingTable1.getRoomNo()+" "+bookingTable1.getDateFrom()+" "+bookingTable1.getDayCount()+" "+bookingTable1.getConfirm());
            }

//        } catch (AbstractJDBCDao.PersistException e) {
//            e.printStackTrace();
//        }

    }


}
