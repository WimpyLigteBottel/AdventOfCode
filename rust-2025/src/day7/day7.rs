use std::collections::VecDeque;

// part 1 took 0.083 ms average (totalRuns=60162; inSeconds=5)
pub(crate) fn part1(result: &[String]) -> String {
    let grid = &mut create_grid(&result);
    let start = grid[0]
        .iter()
        .find(|x| x.value == 'S')
        .expect("should have found start point")
        .clone();

    process_actions((start.row,start.col), grid);

    // count how many beams got split
    grid.iter()
        .flatten()
        .filter(|x| x.value == '^')
        .filter(|x| {
            Point::left(x, grid).value == '1'
                && Point::right(x, grid).value == '1'
                && Point::up(x, grid).value == '1'
        })
        .count()
        .to_string()
}

fn process_actions(start: (usize, usize), grid: &mut [Vec<Point>]) {
    let mut stack: VecDeque<(usize, usize)> = VecDeque::new();
    stack.push_front(start);

    while let Some((row, col)) = stack.pop_front() {
        if row >= grid.len() - 1 {
            continue;
        }

        let next_row = row + 1;

        match grid[next_row][col].value {
            '.' => {
                grid[next_row][col].value = '1'; // used for count later
                stack.push_front((next_row, col));
            }
            '^' => {
                stack.push_front((row, col - 1));
                stack.push_front((row, col + 1));
            }
            _ => {}
        }
    }
}


pub(crate) fn part2(result: &[String]) -> String {
    "".to_string()
}

#[derive(Debug, Clone)]
struct Point {
    col: usize,
    row: usize,
    value: char,
}

impl Point {
    fn up(point: &Point, grid: &Vec<Vec<Point>>) -> Point {
        grid[point.row - 1][point.col].clone()
    }

    fn left(point: &Point, grid: &Vec<Vec<Point>>) -> Point {
        grid[point.row][point.col - 1].clone()
    }

    fn right(point: &Point, grid: &Vec<Vec<Point>>) -> Point {
        grid[point.row][point.col + 1].clone()
    }
}

fn create_grid(result: &[String]) -> Vec<Vec<Point>> {
    let max_size = result.len();
    let mut grid: Vec<Vec<Point>> = Vec::with_capacity(max_size);
    for row in 0..result.len() {
        let mut temp: Vec<Point> = Vec::with_capacity(max_size);
        for col in 0..result[row].len() {
            temp.push(Point {
                col,
                row,
                value: result[row][col..col + 1]
                    .parse()
                    .expect("should have been single char"),
            })
        }
        grid.push(temp)
    }
    grid
}
