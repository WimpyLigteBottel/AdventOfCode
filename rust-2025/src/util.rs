use std::fs::File;
use std::io::Read;
use std::time::{Duration, Instant};

pub(crate) fn read_results(path: &str) -> Vec<String> {
    let mut buffer = String::new();
    let mut f = File::open(path).expect("Unable to open file");
    f.read_to_string(&mut buffer).expect("Unable to read file");

    buffer
        .lines() // splits on \n or \r\n automatically
        .map(|s| s.to_string()) // convert &str → String
        .collect()
}

pub fn avg_time<F>(name: &str, in_seconds: u64, mut work: F)
where
    F: FnMut(),
{
    let end_time = Instant::now() + Duration::from_secs(in_seconds);
    let mut samples = Vec::with_capacity(10_000);

    while Instant::now() < end_time {
        let start = Instant::now();
        work();
        let elapsed = start.elapsed().as_nanos() as u128;
        samples.push(elapsed);
    }

    let avg_ns = samples.iter().copied().sum::<u128>() as f64 / samples.len() as f64;
    let avg_ms = avg_ns / 1_000_000.0;

    println!(
        "{} took {:.3} ms average (totalRuns={}; inSeconds={})",
        name,
        avg_ms,
        samples.len(),
        in_seconds
    );
}
