package ua.opnu;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.*;

public class Task {
    public static void main(String[] args) {

    }

    public void removeShorterStrings(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            String first = list.get(i);
            String second = list.get(i + 1);

            if (first.length() <= second.length()) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
        }
    }

    public void stutter(List<String> list) {
        for (int i = 0; i < list.size(); i += 2) {
            String element = list.get(i);
            list.add(i, element);
        }
    }

    public void switchPairs(List<String> list) {
        for (int i = 0; i < list.size() - 1; i += 2) {
            String temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }
    }

    public void removeDuplicates(List<String> list) {
        if (list.isEmpty()) return;

        int writeIndex = 0;
        for (int readIndex = 1; readIndex < list.size(); readIndex++) {
            if (!list.get(readIndex).equals(list.get(writeIndex))) {
                writeIndex++;
                list.set(writeIndex, list.get(readIndex));
            }
        }
        //видаляємо зайві елементи
        if (writeIndex + 1 < list.size()) {
            list.subList(writeIndex + 1, list.size()).clear();
        }
    }

    public void markLength4(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i++;
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        if (queue.isEmpty()) return true;

        Deque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();

        //додаємо всі елементи в стек
        for (int i = 0; i < size; i++) {
            int value = queue.poll();
            stack.push(value);
            queue.add(value);
        }

        // порівнюємо з черги та стеку
        boolean isPalindrome = true;
        for (int i = 0; i < size; i++) {
            int queueValue = queue.poll();
            int stackValue = stack.pop();

            if (queueValue != stackValue) {
                isPalindrome = false;
            }
            queue.add(queueValue);
        }
        return isPalindrome;
    }

    public void reorder(Queue<Integer> queue) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        //переміщуєм всі елементи з черги в стек
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        //переміщуєм зі стеку в список
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        Collections.sort(list);

        for (Integer x : list) {
            queue.add(x);
        }
    }

    public void rearrange(Queue<Integer> queue) {
        if (queue.isEmpty()) return;

        Deque<Integer> evenStack = new ArrayDeque<>();
        Deque<Integer> oddStack = new ArrayDeque<>();

        int size = queue.size();

        // Розділяємо парні та непарні числа
        for (int i = 0; i < size; i++) {
            int num = queue.poll();
            if (num % 2 == 0) {
                evenStack.push(num);
            } else {
                oddStack.push(num);
            }
        }

        // Додаємо парні числа (в зворотньому порядку через стек)
        while (!evenStack.isEmpty()) {
            queue.offer(evenStack.removeLast());
        }

        // Додаємо непарні числа (в зворотньому порядку через стек)
        while (!oddStack.isEmpty()) {
            queue.offer(oddStack.removeLast());
        }
    }

    public int maxLength(Set<String> set) {
        if (set.isEmpty()) return 0;

        int max = 0;
        for (String str : set) {
            if (str.length() > max) {
                max = str.length();
            }
        }
        return max;
    }

    public void removeEvenLength(Set<String> set) {
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.length() % 2 == 0) {
                iterator.remove();
            }
        }
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);

        set1.retainAll(set2);
        return set1.size();
    }

    public boolean isUnique(Map<String, String> map) {
        Set<String> values = new HashSet<>();

        for (String value : map.values()) {
            if (values.contains(value)) {
                return false;
            }
            values.add(value);
        }
        return true;
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();

        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (map2.containsKey(key) && map2.get(key).equals(value)) {
                result.put(key, value);
            }
        }
        return result;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> result = new HashMap<>();

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            result.put(value, key);
        }
        return result;
    }

    public int rarest(Map<String, Integer> map) {
        if (map.isEmpty()) return 0;

        // Рахуємо частоти
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int value : map.values()) {
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
        }

        // Знаходимо найрідкісніше значення
        int minFrequency = Integer.MAX_VALUE;
        int rarestValue = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int value = entry.getKey();
            int freq = entry.getValue();

            if (freq < minFrequency || (freq == minFrequency && value < rarestValue)) {
                minFrequency = freq;
                rarestValue = value;
            }
        }
        return rarestValue;
    }

    public int maxOccurrences(List<Integer> list) {
        if (list.isEmpty()) return 0;

        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num : list) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        int max = 0;
        for (int count : frequency.values()) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

}
