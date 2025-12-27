use std::cmp::min;
use std::fs::File;
use std::io::Read;

fn get_input() -> Vec<String> {
    let mut buffer = String::new();
    let mut f = File::open("src/input.txt").expect("Unable to open file");
    f.read_to_string(&mut buffer).expect("Unable to read file");

    buffer
        .lines() // splits on \n or \r\n automatically
        .map(|s| s.to_string()) // convert &str → String
        .collect()
}
/*
Fortunately, every present is a box (a perfect right rectangular prism), which makes calculating
the required wrapping paper for each gift a little easier: find the surface area of the box,
 which is 2*l*w + 2*w*h + 2*h*l. The elves also need a little extra paper for each present: the area of the smallest side.

For example:

    A present with dimensions 2x3x4 requires 2*6 + 2*12 + 2*8 = 52 square feet of wrapping paper plus 6 square feet of slack, for a total of 58 square feet.
    A present with dimensions 1x1x10 requires 2*1 + 2*10 + 2*10 = 42 square feet of wrapping paper plus 1 square foot of slack, for a total of 43 square feet.

 */
fn part1() {
    let input = get_input();

    let mut total = 0;
    for x in input {
        total += calculate_wrapping_paper(x)
    }

    println!("{}", total);
}


fn calculate_wrapping_paper(input: String) -> i32 {
    let mut sides: Vec<String> = input.split("x").map(|x| x.to_string()).collect();

    let height: i32 = sides.pop().expect("Height to be filled").parse().unwrap();
    let length: i32 = sides.pop().expect("length to be filled").parse().unwrap();
    let width: i32 = sides.pop().expect("width to be filled").parse().unwrap();

    let a = length * width;
    let b = height * width;
    let c = length * height;

    let little_extra = min(min(a, b), c);

    2 * a + 2 * b + 2 * c + little_extra
}


fn part2() {
    let input = get_input();

    let mut total = 0;
    for x in input {
        total += calculate_bow(x)
    }

    println!("{}", total);
}


/*
For example:

    A present with dimensions 2x3x4 requires 2+2+3+3 = 10 feet of ribbon to wrap the present plus 2*3*4 = 24 feet of ribbon for the bow, for a total of 34 feet.
    A present with dimensions 1x1x10 requires 1+1+1+1 = 4 feet of ribbon to wrap the present plus 1*1*10 = 10 feet of ribbon for the bow, for a total of 14 feet.

 */
fn calculate_bow(input: String) -> i32 {
    let mut sides: Vec<String> = input.split("x").map(|x| x.to_string()).collect();

    let height: i32 = sides.pop().expect("Height to be filled").parse().unwrap();
    let length: i32 = sides.pop().expect("length to be filled").parse().unwrap();
    let width: i32 = sides.pop().expect("width to be filled").parse().unwrap();

    let ribbon = length * width * height;

    let smallest_a = min(length, width) * 2;
    let smallest_b = min(width, height) * 2;
    let smallest_c = min(length, height) * 2;

    let smallest_ribbon_a = smallest_c + smallest_a;
    let smallest_ribbon_b = smallest_b + smallest_c;

    if (smallest_ribbon_a > smallest_ribbon_b) {
        return ribbon + smallest_ribbon_a;
    }

    ribbon + smallest_ribbon_b
}



fn main() {
    // part1();
    part2();
}
