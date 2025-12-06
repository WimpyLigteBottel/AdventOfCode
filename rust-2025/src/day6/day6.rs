struct OperatorAndIndex {
    operator: char,
    index: usize,
}

// part 1 took 0.022 ms average (totalRuns=224142; inSeconds=5)
pub(crate) fn part1(result: &[String]) -> String {
    let operator = get_operators_and_starting_index(result);

    let mut total: i128 = 0;

    for x in 0..operator.len() - 1 {
        let current = operator[x].index;
        let next = operator[x + 1].index; // <-- FIXED

        let new_list = [
            fast_parse(&result[0][current..next]),
            fast_parse(&result[1][current..next]),
            fast_parse(&result[2][current..next]),
            fast_parse(&result[3][current..next]),
        ];

        total += calculate_answer(operator[x].operator, Vec::from(new_list));
    }

    total.to_string()
}

fn calculate_answer(operator: char, new_list: Vec<i128>) -> i128 {
    if operator == '*' {
        return new_list.iter().product();
    } else if operator == '+' {
        return new_list.iter().sum();
    }
    0
}

fn get_operators_and_starting_index(result: &[String]) -> Vec<OperatorAndIndex> {
    let mut operators: Vec<OperatorAndIndex> = Vec::new();

    for index in 0..result[0].len() {
        //select teh operator
        let operator: char = result[4][index..index + 1].parse().unwrap();

        if operator != ' ' {
            operators.push(OperatorAndIndex {
                operator: operator,
                index,
            })
        }
    }

    operators
}

// part 2 took 0.045 ms average (totalRuns=111675; inSeconds=5)
pub(crate) fn part2(result: &[String]) -> String {
    let operator = get_operators_and_starting_index(&result);

    let mut total: i128 = 0;

    for x in 0..operator.len() - 1 {
        let current = operator[x].index;
        let next = operator[x + 1].index;
        let width = next - current - 1;

        // Each "column" yields one number built from 4 rows
        let mut new_list = Vec::with_capacity(width);

        for i in 0..width {
            let mut value: i128 = 0;

            // Read one digit from each row (only ASCII digits)
            for row in 0..4 {
                let c = result[row].as_bytes()[current + i];
                if c != b' ' {
                    value = value * 10 + (c - b'0') as i128;
                }
            }

            new_list.push(value);
        }

        total += calculate_answer(operator[x].operator, new_list);
    }

    total.to_string()
}

// parses digits without allocating a String
fn fast_parse(s: &str) -> i128 {
    let mut out = 0i128;
    for c in s.bytes() {
        if c != b' ' {
            out = out * 10 + (c - b'0') as i128;
        }
    }
    out
}
