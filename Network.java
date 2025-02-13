/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }
    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        for (int i = 0; i < this.userCount; i++) {
            if (name.equalsIgnoreCase(this.users[i].getName())) {
                return this.users[i];
            }
        }
        return null;
    }


    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        //// Replace the following statement with your code
        if (this.userCount == this.users.length || this.getUser(name) != null) {
            return false;
        }

        this.users[this.userCount++] = new User(name);
        return true;
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        if (name1 == null || name2 == null) {
            return false;
        }

        User user1 = this.getUser(name1);
        User user2 = this.getUser(name2);

        if (user1 == null || user2 == null || name1.equalsIgnoreCase(name2) || user1.follows(name2)) {
            return false;
        }


        return user1.addFollowee(name2);
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        //// Replace the following statement with your code
        User user = this.getUser(name);
        User maxUser = null;
        int maxMutual = -1;
        for (int i = 0; i < this.userCount; i++) {
            if (this.users[i].getName().equalsIgnoreCase(user.getName())) {
                continue;
            }
            int currMutual = user.countMutual(this.users[i]);
            if (currMutual > maxMutual) {
                maxUser = this.users[i];
                maxMutual = currMutual;
            }
        }
        return maxUser.getName();
    }
    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        User mostPopularUser = null;
        int maxCount = -1;
        for (int i = 0; i < this.userCount; i++) {
            int count = followeeCount(this.users[i].getName());
            if (count > maxCount) {
                mostPopularUser = this.users[i];
                maxCount = count;
            }
        }
        if (mostPopularUser == null) {
            return null;
        }
        return mostPopularUser.getName();
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        //// Replace the following statement with your code
        int count = 0;
        for (int i = 0; i < this.userCount; i++) {
            if (users[i].follows(name)) {
                count++;
            }
        }
        return count;
    }

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
       String s = "Network:\n";
         for (int i = 0; i < this.userCount; i++) {
              s += this.users[i] + "\n";
         }
       return s.substring(0, s.length() - 1);
    }
}
