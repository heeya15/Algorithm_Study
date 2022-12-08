function solution(food) {
  var answer = "";

  food.forEach((v, i) => {
    if (v > 1) {
      const a = parseInt(v / 2);
      console.log(a, v);
      answer += String(i).repeat(a);
    }
  });
  answer = answer + "0" + answer.split("").reverse().join("");
  return answer;
}
