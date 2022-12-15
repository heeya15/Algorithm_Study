function solution(cacheSize, cities) {
  // 어짜피 캐시 사이즈는 30 단순 정렬로도 가능!

  let arr = new Array();
  let num = 0;

  if (cacheSize === 0) {
    return 5 * cities.length;
  }

  cities.forEach((v) => {
    const lowerString = v.toLowerCase();
    if (arr.includes(lowerString)) {
      num += 1;
      arr.splice(arr.indexOf(lowerString), 1);
    } else {
      num += 5;
      if (arr.length >= cacheSize) {
        arr.shift();
      }
    }

    arr.push(lowerString);
  });
  return num;
}
