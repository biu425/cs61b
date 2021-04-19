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
}