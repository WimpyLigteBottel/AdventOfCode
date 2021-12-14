package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/*


start-RW-he
start-DX
start-pj-DX
start-pj-he
start-pj-RW
start-pj-fs-he
fs-end
fs-he
fs-DX
zg-sl
zg-pj
zg-RW
zg-he
he-DX
he-WI
end-zg


 */
public class Day12 {

    public List<String> readInput(boolean readExample) throws IOException {

        String path = "src\\main\\resources\\day11";
        if (readExample) {
            path = "src\\main\\resources\\day11Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(stringList);

    }

    public long part1(List<String> input) {


        Map<String, String> map = new TreeMap<>();

        input.forEach(s -> {
            map.put(s, s);
        });

        List<String> start = getAllValuesThatValueCanGoTo("start", input);

        Set<String> validPaths = new HashSet<>();

        start.forEach(s -> {
            findPath("start", s, input, validPaths);
        });

        return validPaths.size();
    }

    private String findPath(String path, String currentValue, List<String> input, Set<String> validPath) {
        path += "," + currentValue;
        if (path.contains("end")) {
            if (path.contains("start") && path.contains("end")) {
                validPath.add(path);
            }
            return path;
        }

        List<String> goTo = getAllValuesThatValueCanGoTo(currentValue, input);

        for (String s : goTo) {

            boolean isSmallCave = s.charAt(0) >= 'a';
            if (path.contains(s) && isSmallCave) {
                continue;
            }

            if (s.equals("start")) {
                continue;
            }


            findPath(path, s, input, validPath);
        }

        return path;
    }

    private String findPathPart2(String path, String lastValue, List<String> input, Set<String> validPath, boolean duplicateUsed) {
        path += "," + lastValue;
        if (lastValue.equals("end")) {
            validPath.add(path);
            return path;
        }

        //No character allowed above 2 if its small
        String[] split = path.split(",");
        for (int x = 0; x < split.length; x++) {
            long characterAlreadyCount = countCharacter(path, split[x]);
            boolean isCaveSmall = split[x].charAt(0) >= 'a';

            if (characterAlreadyCount > 2 && isCaveSmall) {
                return path;
            }
        }

        List<String> goTo = getAllValuesThatValueCanGoTo(lastValue, input);
        goTo.remove("start");

        for (String nextCharacter : goTo) {
            boolean isSmallCave = nextCharacter.charAt(0) >= 'a';
            if (duplicateUsed && countCharacter(path, nextCharacter) > 2 && path.contains(nextCharacter) && isSmallCave) {
                continue;
            }

            boolean hasDuplicateTemp = false;
            split = path.split(",");
            split = Arrays.stream(split).filter(s -> !s.equals("start")).toArray(String[]::new);

            findPathPart2(path, nextCharacter, input, validPath, true);
            findPathPart2(path, nextCharacter, input, validPath, false);
        }


        return path;
    }

    public long countCharacter(String path, String character) {

        return Arrays.stream(path.split(","))
                .filter(s -> s.equals(character))
                .count();
    }

    private List<String> getAllValuesThatValueCanGoTo(String character, List<String> map) {
        return map.stream()
                .filter(s -> s.contains(character))
                .filter(s -> s.split("-")[0].equals(character) || s.split("-")[1].equals(character))
                .map(s -> {
                    String[] parts = s.split("-");
                    if (parts[0].equals(character)) {
                        return parts[1];
                    }
                    return parts[0];
                }).collect(Collectors.toList());
    }


    public long part2(List<String> input) {

        Map<String, String> map = new TreeMap<>();

        input.forEach(s -> {
            map.put(s, s);
        });

        List<String> start = getAllValuesThatValueCanGoTo("start", input);

        Set<String> validPaths = new TreeSet<>();

        start.forEach(s -> {
            findPathPart2("start", s, input, validPaths, false);
        });

        return validPaths.size();
    }

}
