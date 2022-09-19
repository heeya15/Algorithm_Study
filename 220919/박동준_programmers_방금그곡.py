def solution(m, musicinfos):
    answer = ''
    # m 멜로디
    
    # 재생된 분 차이 시간 -> 분 변환 -> 길이 넘을때는 음을 추가로 제공해야함
    # 최대 재생시간 24를 넘기지는 않음
    
    # 조건이 일치할때, 시간이 제일긴거 --> 시간도 같으면 먼저 입력 된 음악
    same_music = []
    # 전처리
    music =[]
    for i in musicinfos:
        
        musicinfo = i.split(",")
        start_time = musicinfo[0]
        end_time = musicinfo[1]
        melody = musicinfo[3]
        title = musicinfo[2]
        run_time = (int(end_time.split(':')[0])*60 + int(end_time.split(':')[1])) - (int(start_time.split(':')[0])*60 + int(start_time.split(':')[1]))
        
        run_melody = []
        time = 0
        
        list_melody = []
        for k in range(len(melody)):
            if melody[k] != '#':
                list_melody.append(melody[k])
            else:
                list_melody[-1] += '#'
                
        for mm in range(run_time):
            run_melody.append(list_melody[mm % len(list_melody)])
        
        # print(run_melody)
        
        music.append([run_time,run_melody,title])
    
    new_m = []
    for kk in range(len(m)):
            if m[kk] != '#':
                new_m.append(m[kk])
            else:
                new_m[-1] += '#'
    for time, sound, title in music:
        # print(sound, new_m,'휴')
        start,end = 0,len(new_m)
        word = sound[start:end]
        flag = False
        while end <= len(sound):
            if word == new_m:
                flag = True
                break
            else:
                start += 1
                end += 1
                word = sound[start:end]
        
        if flag:
            same_music.append([time,title])
    
    print(same_music)
    if same_music:
        same_music.sort(key = lambda x: -x[0])
        answer = same_music[0][1]
    else:
        answer = '(None)'
    
    
    return answer