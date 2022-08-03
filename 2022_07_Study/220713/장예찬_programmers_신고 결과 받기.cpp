#include <string>
#include <vector>
#include <map>
#include <set>
#include <iostream>
#include <sstream>

using namespace std;

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
    
    //인덱스 저장
    map<string, int> id_idx_m;
    //KVS 맵 저장
    map<string, set<string>> reported_m;
    for(int i=0, end = id_list.size(); i<end; i++){
        id_idx_m[id_list[i]] = i;
    }    
    
    for (auto rep : report){
        stringstream sub(rep);
        string from, to;
        sub >> from >> to;
        
        reported_m[to].insert(from);
    }
    
    //정답 저장
    vector<int> answer(id_list.size(), 0);
    
    for (auto iter : reported_m){
        if (iter.second.size() >= k){
            for (auto in_iter : iter.second){
                answer[id_idx_m[in_iter]]++;
            }
        }
    }    
    
    return answer;
}