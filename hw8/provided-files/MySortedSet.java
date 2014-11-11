import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Collections;

/**
 * An implementation of the FunctionalSortedSet interface that uses an ArrayList
 * as the backing data structure.
 *
 * @author Joe Rossi
 * @version 1.0
 * @param <E> A comparable object that is contained within this sorted set.
 */
public class MySortedSet<E extends Comparable<? super E>>
    implements FunctionalSortedSet<E> {

    private ArrayList<E> list;
    private Comparator<E> c;

    /**
     * Creates a MySortedSet using the Comparable's compareTo as Comparator
     */
    public MySortedSet() {
        this(null);
    }

    /**
     * Creates a MySortedSet using a specific Comparator
     *
     * @param c a Comparator that either "has" or "is" one int valued method
     */
    public MySortedSet(Comparator<E> c) {
        this.c = c;
        list = new ArrayList<E>();
    }

    //-----------FunctionalSortedSet METHODS - ONLY MODIFY filter!!------------

    @Override
    public MySortedSet<E> filter(Predicate<E> p) {
        return list.stream().filter(p).collect(
            Collectors.toCollection(() -> new MySortedSet())).sort(c);
    }

    @Override
    public MySortedSet<E> sort(Comparator<E> comparator) {
        MySortedSet<E> ret = new MySortedSet<E>(comparator);
        ret.addAll(this.list);
        return ret;
    }

    //------SortedSet METHODS - ONLY MODIFY subSet and tailSet!!---------------

    @Override
    public Comparator<? super E> comparator() {
        return c;
    }

    /**
     * gets the first item in the set
     * @return returns first item in set
     */
    @Override
    public E first() {
        return list.get(0);
    }

    /**
     * Returns a MySortedSet object with all elements [first, toElement) using a
     * functional programming expression.
     *
     * @param toElement The element the returned set should stop before.
     * @return A sorted set containing elements strictly less than toElement.
     */
    @Override
    public MySortedSet<E> headSet(E toElement) {
        return list.subList(0, list.indexOf(toElement)).stream().collect(
            Collectors.toCollection(() -> new MySortedSet<>(c)));
    }

    /**
     * Return a MySortedSet object with all elements [fromElement, toElement)
     * using a functional programming expression.
     *
     * @param fromElement The first element the returned set should contain.
     * @param toElement The element the returned set should stop before.
     * @return A sorted set containing elements fromElement to toElement.
     */
    @Override
    public MySortedSet<E> subSet(E fromElement, E toElement) {
        return list.subList(list.indexOf(fromElement), list.indexOf(toElement)
            ).stream().collect(
            Collectors.toCollection(() -> new MySortedSet<>(c)));
    }

    /**
     * Return a MySortedSet object with all elements [fromElement, last]
     * using a functional programming expression.
     *
     * @param fromElement The first element the returned set should contain.
     * @return A sorted set containing elements fromElement and onwards.
     */
    @Override
    public MySortedSet<E> tailSet(E fromElement) {
        return list.subList(list.indexOf(fromElement), list.indexOf(last(
            ))).stream().collect(
            Collectors.toCollection(() -> new MySortedSet<>(c)));
    }

    /**
     * gets last item in set
     * @return returns last item in set
     */
    @Override
    public E last() {
        return list.get(list.size() - 1);
    }

    //-----------Set METHODS - TODO---------------------------------------------

    /**
     * adds an item only if it is unique to the set
     * @param  e item to add
     * @return   true if added a unique item
     */
    @Override
    public boolean add(E e) {
        if (list.contains(e)) {
            return false;
        }
        return list.add(e);
    }

    /**
     * removes item from set
     * @param  e item to remove
     * @return   true if the set was changed
     */
    @Override
    public boolean remove(Object e) {
        return list.remove(e);
    }

    /**
     * checks to see if item is in set
     * @param  e item to check
     * @return   true if item is already in set
     */
    @Override
    public boolean contains(Object e) {
        return list.contains(e);
    }

    /**
     * checks to see if every item in col is in set
     * @param  col collection to check
     * @return     true if its in the set
     */
    @Override
    public boolean containsAll(Collection<?> col) {
        return list.containsAll(col);
    }

    /**
     * checks to see if set contains any items
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * finds the number of items in set
     * @return returns num of items in set
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * adds every item of col in set
     * @param  col collection of items to add
     * @return     true if set was changed
     */
    @Override
    public boolean addAll(Collection<? extends E> col) {
        boolean bool = false;
        for (E e : col) {
            if (add(e)) {
                bool = true;
            }
        }
        Collections.sort(list, c);
        return bool;
    }

    /**
     * removes every item in col from set
     * @param  col collection of items to remove
     * @return     true if set was changed
     */
    @Override
    public boolean removeAll(Collection<?> col) {
        return list.removeAll(col);
    }

    /**
     * removes every item besides that retained
     * @param  col collection of items to retain
     * @return true if set was changed
     */
    @Override
    public boolean retainAll(Collection<?> col) {
        return list.retainAll(col);
    }

    /**
     * an iterator for the backing array list
     * @return an iterator object
     */
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    /**
     * clears the list of all items
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * creates an array of appropiate size and populates it with items from set
     * @return array of type of item
     */
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * creates an array of appropiate size and populates it with items from set
     * @param a an array to be populated
     * @return array of type of item
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    /**
     * prints each item in a new line
     * @return string each item
     */
    @Override
    public String toString() {
        String s = "";
        for (E e : list) {
            s += e + "\n";
        }
        return s;
    }
}
