struct OperatorAndIndex {
    operator: char,
    index: usize,
}

// part 1 took 0.031 ms average (totalRuns=163366; inSeconds=5)
pub(crate) fn part1(result: &[String]) -> String {
    let operator = get_operators_and_starting_index(result);

    let mut total: i128 = 0;

    for x in 0..operator.len() - 1 {
        let current = operator[x].index;
        let next = operator[x + 1].index; // <-- FIXED

        let new_list = [
            result[0][current..next].trim().parse::<i128>().unwrap(),
            result[1][current..next].trim().parse::<i128>().unwrap(),
            result[2][current..next].trim().parse::<i128>().unwrap(),
            result[3][current..next].trim().parse::<i128>().unwrap(),
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

// part 2 took 0.373 ms average (totalRuns=24780;; inSeconds=5)
pub(crate) fn part2(result: &[String]) -> String {
    let operator = get_operators_and_starting_index(&result);

    let mut total: i128 = 0;

    for x in 0..operator.len() - 1 {
        let current = operator[x].index;
        let next = operator[x + 1].index; // <-- FIXED

        let new_list: Vec<i128> = (0..next - current - 1)
            .map(|y| {
                let one = &result[0][current..next][y..y + 1].trim();
                let two = &result[1][current..next][y..y + 1].trim();
                let three = &result[2][current..next][y..y + 1].trim();
                let four = &result[3][current..next][y..y + 1].trim();

                let new = [*one, *two, *three, *four].join("");

                new.parse::<i128>().unwrap()
            })
            .collect();

        total += calculate_answer(operator[x].operator, new_list)
    }

    total.to_string()
}
