import java.util.Locale;

/**
 * Represents a user in a social network. A user is characterized by a name,
 * a list of user names that s/he follows, and the list's size.
 */
public class User {

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /**
     * Creates a user with an empty list of followees.
     */
    public User(String name) {
        this.name = name;
        follows = new String[maxfCount]; // fixed-size array for storing followees
        fCount = 0;                      // initial number of followees
    }

    /**
     * Creates a user with some followees. The only purpose of this constructor is
     * to allow testing the toString and follows methods, before implementing other methods.
     */
    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /**
     * Returns the name of this user.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the follows array.
     */
    public String[] getfFollows() {
        return follows;
    }

    /**
     * Returns the number of users that this user follows.
     */
    public int getfCount() {
        return fCount;
    }

    /**
     * If this user follows the given name, returns true; otherwise returns false.
     */
    public boolean follows(String name) {
        //// Replace the following statement with your code
        for (int i = 0; i < this.fCount; i++) {
            if (name.equalsIgnoreCase(this.follows[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes this user follow the given name. If successful, returns true.
     * If this user already follows the given name, or if the follows list is full, does nothing and returns false;
     */
    public boolean addFollowee(String name) {
        //// Replace the following statement with your code
        if (this.fCount == this.follows.length || this.follows(name)) {
            return false;
        }
        this.follows[this.fCount++] = name;
        return true;
    }

    /**
     * Removes the given name from the follows list of this user. If successful, returns true.
     * If the name is not in the list, does nothing and returns false.
     */
    public boolean removeFollowee(String name) {
        //// Replace the following statement with your code
        if (this.fCount == 0) {
            return false;
        }
        if (!this.follows(name)) {
            return false;
        }
        boolean found = false;
        for (int i = 0; i < this.fCount; i++) {
            if (found) {
                this.follows[i - 1] = this.follows[i];
            }
            if (this.follows[i].equals(name)) {
                found = true;
            }
        }
        this.fCount--;
        return found;
    }

    /**
     * Counts the number of users that both this user and the other user follow.
     * /*  Notice: This is the size of the intersection of the two follows lists.
     */
    public int countMutual(User other) {
        //// Replace the following statement with your code
        int count = 0;
        for (int i = 0; i < this.fCount; i++) {
            for (int j = 0; j < other.fCount; j++) {
                if (this.follows[i].equals(other.follows[j])) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Checks is this user is a friend of the other user.
     * (if two users follow each other, they are said to be "friends.")
     */
    public boolean isFriendOf(User other) {
        //// Replace the following statement with your code
        return (this.follows(other.name) && other.follows(this.name));
    }

    /**
     * Returns this user's name, and the names that s/he follows.
     */
    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}
