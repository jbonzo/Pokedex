import java.util.Comparator;

/**
 * Represents a Pokedex - basically a Pokemon encyclopedia that adds new entries
 * when you encounter a Pokemon for the first time.
 * It also provides methods for organizing its information in useful ways.
 *
 * @author Joe Rossi
 * @version 1.0
 */
public class Pokedex {

    // ------ Instance data here ------

    private MySortedSet<Pokemon> set;

    /**
     * Constructs a Pokedex object by setting up the sorted set of Pokemon
     */
    public Pokedex() {
        Comparator<Pokemon> c = (a, b) -> a.compareTo(b);
        set = new MySortedSet<Pokemon>(c);
    }

    @Override
    public String toString() {
        MySortedSet<Pokemon> temp = new MySortedSet<Pokemon>();
        temp = set.sort((a, b) -> a.compareTo(b));
        String s = "";
        for (Pokemon p : temp) {
            s += p.toString() + "\n";
        }
        return s;
    }

    /**
     * Adds a Pokemon to the sorted set
     *
     * @param p the Pokemon to be added
     * @return true if the pokemon was not already in the set, false otherwise
     */
    public boolean add(Pokemon p) {
        return set.add(p);
    }

    /**
     * Returns the number of Pokemon in the Pokedex
     *
     * @return  the number of Pokemon in the Pokedex
     */
    public int countPokemon() {
        return set.size();
    }

    /**
     * Clear the Pokedex and start over
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns a set of alphabetized Pokemon, using a lambda expression
     *
     * @return  the alphabetized set
     */
    public MySortedSet<Pokemon> listAlphabetically() {
        return set.sort((a, b) -> a.getName().compareTo(b.getName()));
    }

    /**
     * Returns a set of Pokemon grouped by type, using a lambda expression
     *
     * @return  the grouped by primary type set
     */
    public MySortedSet<Pokemon> groupByPrimaryType() {
        return set.sort((a, b) -> a.getPrimaryType().compareTo(
            b.getPrimaryType()));
    }

    /**
     * Returns a set of all Pokemon of type t
     *
     * @param t the type we want listed
     * @return  the set of all Pokemon in the Pokedex of type t
     */
    public MySortedSet<Pokemon> listByType(PokemonType t) {
        return set.filter((p) -> p.getPrimaryType() == t
            || p.getSecondaryType() == t);
    }

    /**
     * Returns a set of Pokemon with numbers in the range [start, end]
     *
     * @param start the first number in the new set
     * @param end   the last number in the new set
     * @return  the set containing all Pokemon in the Pokedex from [start, end]
     */
    public MySortedSet<Pokemon> listRange(int start, int end) {
        return set.filter((p) -> p.getNumber() >= start
            && p.getNumber() <= end);
    }
}

