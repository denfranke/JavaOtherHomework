package Module_additional1.folder7;

import java.util.*;

public class DAG<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> from, Vertex<T> to) {
        // добавляем в список смежности только в одном направлении
        from.getAdjacent().add(to);
    }

    public int path(Vertex<T> from, Vertex<T> to) {
        Map<Vertex<T>, Integer> paths = new HashMap<>(); // по городу даёт количество полётов чтобы до него добраться из from
        paths.put(from, 0); // добраться из пункта отправления до него же самого можно вообще не летая

        Queue<Vertex<T>> queue = new ArrayDeque<>(); // очередь обхода вершин
        Set<Vertex<T>> added = new HashSet<>();
        queue.add(from);

        while (!queue.isEmpty()) { // пока очередь не пуста
            Vertex<T> v = queue.poll(); // вынимаем следующий элемент из очереди

            if (!v.equals(to)) {
                for (Vertex<T> tVertex : v.getAdjacent()) {
                    if (added.contains(tVertex)) {
                        continue;
                    } else {
                        queue.add(tVertex);
                        added.add(tVertex);
                        paths.put(tVertex, paths.get(v) + 1);
                    }
                }
            } else
                return paths.get(v);
        }
        return -1;
    }

}
