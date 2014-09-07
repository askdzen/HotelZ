package com.epam.ad.action;


import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private final static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        actions.put("GET/", new ShowLoginPageAction()); //переводит на страницу Приветствия, если Пользователь залогинился, иначе на страницу авторизации
        actions.put("POST/", new LoginAction());// проверяет на соответствие введенные логин и пароль и перенаправляет на страницу приветствия при верной валидации пользователя с ролью "Клиент" или на страницу с формой админа, если роль "Админ"
        actions.put("GET/registration", new ShowPageAction("registration"));// * обеспечивает переход к странице через сервлет, а не напрямую
        actions.put("POST/registration", new RegistrationAction());//проверяет корректность ввода пароля и его подтверждения, корректность на уникальный логин, устанавливает в атрибут поздравления при успешной регистрации и перенаправляет на страницу приветствия
        actions.put("GET/adminform", new ShowPageAction("adminform"));//*
        actions.put("GET/bookingtable", new BookingTableAction());//**осуществляет вывод всех строк таблицы BookingTable и устанавливает атрибуты пагинации
        actions.put("POST/bookingtable",new BookingTableEditAction());// изменяет данные таблицы BookingTable(операции CRUD), а также подтверждает бронирование заказа
        actions.put("GET/customerdetail", new CustomerTableAction());//**
        actions.put("POST/customerdetail", new CustomerEditAction());//изменяет данные таблицы CustDetail(операции CRUD)
        actions.put("GET/roomdetail", new RoomTableAction());//**
        actions.put("POST/roomdetail",new RoomEditAction());
        actions.put("GET/userdetail", new UserAction());
        actions.put("POST/userdetail",new UserEditAction());
        actions.put("GET/account", new AccountAction()); // ищет в таблице заказов все заказы, залогинившегося пользователя; по полям заказа "ID комнаты" и "ID данных о клиенте" выводит подробности заказа в виде двух табличных строк
        actions.put("POST/account",new AccountEditAction());
        actions.put("GET/welcome", new ShowPageAction("welcome"));//*
        actions.put("POST/welcome", new GetAvailableRoomAction());// выбирает свободный номер для бронирования в соответствии с выбранными пользователем параметрами и перенаправляет на страницу подтверждения и заполнения данных гостя/клиента (если все номера с данными параметрами заняты - выводит соотв. сообщение)
        actions.put("GET/reservation", new ShowPageAction("reservation"));//*
        actions.put("POST/reservation", new ConfirmAction());// получает параметры заказа/бронирования и данных клиента и заносит все в соответствующие таблицы (при удачном выполнении переводит на страницу приветствия с оповещением, что номер забронирован)
        actions.put("GET/bookingtablecreate",new ShowPageAction("bookingtablecreate"));
        actions.put("POST/bookingtablecreate",new BookingTableCreateAction());
        actions.put("GET/customercreate",new ShowPageAction("customercreate"));
        actions.put("POST/customercreate",new CustomerCreateAction());
        actions.put("GET/roomcreate",new ShowPageAction("roomcreate"));
        actions.put("POST/roomcreate",new RoomCreateAction());
        actions.put("GET/usercreate",new ShowPageAction("usercreate"));
        actions.put("POST/usercreate",new UserCreateAction());

    }

    public static Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
