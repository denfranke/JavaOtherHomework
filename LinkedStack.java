package Module_additional;

public class LinkedStack {
    private Node tail; // ссылка на последний добавленный узел (обёртку)
    private int size; // размер стека, т.е. количество элементов в нём

    public void push(int value) {
        Node node = new Node(value); // создаём новый узел
        if (tail != null) { // если в стеке уже есть элементы
            node.setPrev(tail); // связываем новый узел с последним
        }
        tail = node; // назначаем новый узел последним узлом
        size++; // увеличиваем счётчик элементов
    }

    public int pop() {
        if (!isEmpty()) {
            int value = tail.getValue();
            tail = tail.getPrev();
            size--;
            return value;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() <= 0;
    }

    public String toString() {
        Node t = tail;
        StringBuilder s = new StringBuilder();
        if (t != null) {
            s.append(t.getValue());
            t = t.getPrev();
        }
        while (t != null) {
            s.append(" -> ").append(t.getValue());
            t = t.getPrev();
        }
        return s.toString();
        // если есть элементы, пройдитесь по связному списку,
        // выводя элементы.
        // вывод должен быть в точности как в комментариях к main
        // при этом этот метод не должен менять стек!
    }
}

