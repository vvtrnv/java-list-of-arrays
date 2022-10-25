package realization.types.factory;

import realization.types.userTypes.IntegerUserType;
import realization.types.userTypes.Point2DUserType;
import realization.types.userTypes.UserType;

import java.util.ArrayList;

/**
 * Фабрика, возвращающая класс по названию типа данных
 */
public class FactoryUserType {

    /**
     * Получить список типов данных
     * @return
     */
    public ArrayList<String> getNamesOfTypeList() {
        ArrayList<String> namesList = new ArrayList<>();

        namesList.add("Integer");
        namesList.add("Point2D");

        return namesList;
    }

    /**
     * Получить объект желаемого ТД
     * @param usertype название ТД
     * @return
     */
    public UserType getBuilderByTypeName(String usertype) {
        switch (usertype) {
            case "Integer":
                return new IntegerUserType();
            case "Point2D":
                return new Point2DUserType();
            default:
                try {
                    throw new Exception("Ошибка. Не найден тип данных");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
        }
    }
}
