import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		String bombWord = br.readLine();
		
		for (int i = 0; i < origin.length(); i++) {
			sb.append(origin.charAt(i));

			// 폭발 문자열과 크기가 같아지는 순간부터 체크
			if (sb.length() >= bombWord.length()) {
				boolean isBomb = true;

                // 하나씩 비교하며 폭발 문자열인지 확인
				for (int j = 0; j < bombWord.length(); j++) {
					if (sb.charAt(sb.length() - bombWord.length() + j) != bombWord.charAt(j)) {
						isBomb = false;
						break;
					};
				}

                // 폭발 문자열이 확인되면 제거
				if (isBomb) {
					sb.delete(sb.length() - bombWord.length(), sb.length());
				}
			}
		}
		
		if (sb.length() == 0) System.out.println("FRULA");
		else System.out.println(sb);
	}
}