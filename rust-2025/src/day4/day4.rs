use std::collections::HashMap;

//part 1 took 1.518 ms average (totalRuns=3295; inSeconds=5)
pub(crate) fn part1(result: &[String]) -> String {
    let toilet_paper_map = toilet_paper(result);

    let targets = rolls_to_remove(&toilet_paper_map, 4).iter().count();

    targets.to_string()
}

// part 2 took 22.959 ms average (totalRuns=218; inSeconds=5)
pub(crate) fn part2(result: &[String]) -> String {
    let mut toilet_paper_map = toilet_paper(result);

    let mut has_changed = true;

    while has_changed {
        has_changed = false;

        let to_remove: Vec<Point> = rolls_to_remove(&toilet_paper_map, 4);

        for key in to_remove {
            toilet_paper_map.remove(&key);
            toilet_paper_map.insert(key, 'X');
            has_changed = true;
        }
    }

    toilet_paper_map
        .iter()
        .filter(|x| (*x.1).eq(&'X'))
        .count()
        .to_string()
}

fn rolls_to_remove(map: &HashMap<Point, char>, count: usize) -> Vec<Point> {
    map.iter()
        .filter(|(_, val)| (*val).eq(&'@') )
        .filter(|(key, _)| {
            let count_at = around(key)
                .iter()
                .filter_map(|p| map.get(p))
                .filter(|v| (*v).eq(&'@'))
                .count();
            count_at < count
        })
        .map(|(k, _)| k.clone())
        .collect::<Vec<Point>>()
}

#[derive(Hash, Eq, PartialEq, Debug, Clone)]
struct Point(i32, i32);

fn around(point: &Point) -> [Point; 8] {
    let x = point.0;
    let y = point.1;

    let up = Point(x, y - 1);
    let up_left = Point(x - 1, y - 1);
    let up_right = Point(x + 1, y - 1);

    let down = Point(x, y + 1);
    let down_left = Point(x - 1, y + 1);
    let down_right = Point(x + 1, y + 1);

    let left = Point(x - 1, y);
    let right = Point(x + 1, y);

   [
        up, up_left, up_right, down, down_left, down_right, left, right,
    ]
}

fn toilet_paper(result: &[String]) -> HashMap<Point, char> {
    let temp: Vec<Vec<char>> = result.iter().map(|x| x.chars().collect()).collect();

    let mut map: HashMap<Point, char> = HashMap::new();

    for y in 0..result.len() as i32 {
        for x in 0..result[y as usize].len() as i32 {
            map.insert(Point(x, y).clone(),  temp[y as usize][x as usize]);
        }
    }

    map
}
