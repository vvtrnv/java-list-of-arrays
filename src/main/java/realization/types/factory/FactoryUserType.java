package realization.types.factory;

import realization.types.userTypes.IntegerUserType;
import realization.types.userTypes.Point2DUserType;
import realization.types.userTypes.UserType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Фабрика, возвращающая класс по названию типа данных
 */
public class FactoryUserType {

    private final static ArrayList<UserType> listOfAvailableTypes = new ArrayList<>();

    static {
        ArrayList<UserType> types = new ArrayList<>(Arrays.asList(new IntegerUserType(), new Point2DUserType()));
        listOfAvailableTypes.addAll(types);
    }

    /**
     * Получить объект желаемого ТД
     * @param typename название ТД
     * @return
     */
    public UserType getBuilderByTypeName(String typename) {
        for (UserType userType : listOfAvailableTypes) {
            if (typename.equals(userType.typeName())) {
                return userType;
            }
        }

        throw new RuntimeException("Ошибка. Тип данных не найден!");
    }

    /**
     * Получить список типов данных
     * @return
     */
    public ArrayList<String> getTypeNameList() {
        ArrayList<String> typeNameListString = new ArrayList<>();
        for (UserType userType : listOfAvailableTypes) {
            typeNameListString.add(userType.typeName());
        }
        return typeNameListString;
    }


}
