public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dequeInOrder = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dequeInOrder.addLast(word.charAt(i));
        }
        return dequeInOrder;
    }

    // use recursion
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            Character head = deque.removeFirst();
            Character tail = deque.removeLast();
            if (head == tail) {
                return isPalindromeHelper(deque);
            } else {
                return false;
            }
        }
    }

    //**return true if the word is a palindrome according to the character comparison test provided
    // by the CharacterComparator passed in as argument cc */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque((word));
        return isPalindromeHelper(deque, cc);
    }
    private boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            Character head = deque.removeFirst();
            Character tail = deque.removeLast();
            if (cc.equalChars(head, tail)) {
                return isPalindromeHelper(deque, cc);
            } else {
                return false;
            }
        }
    }
}
