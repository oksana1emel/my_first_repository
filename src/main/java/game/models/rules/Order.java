package game.models.rules;
import game.models.TypeOfFigures;

/**
 Класс контейнер для того, чтобы хранить тип фигуры и кол-во фигур такого типа.
 */

public class Order {

    private TypeOfFigures type;
    private int quantity;


    public Order (TypeOfFigures type, int quantity ) {
        this.type = type;
        this.quantity = quantity;
    }

    public TypeOfFigures getType () { return type; }

    public int getQuantity () { return quantity; }
}
