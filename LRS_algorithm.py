import requests
from bs4 import BeautifulSoup
import time
import urllib.request #
from selenium.webdriver import Chrome
import re
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
import datetime as dt
import pandas as pd
from pandas import DataFrame
from selenium.webdriver import ActionChains
import json
import re
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
import datetime as dt
from nltk.sentiment.vader import SentimentIntensityAnalyzer
from sklearn.metrics import accuracy_score, confusion_matrix, precision_score
from sklearn.metrics import recall_score, f1_score
import pandas as pd
import nltk
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.pipeline import Pipeline
from sklearn.metrics import accuracy_score, roc_auc_score
import re
import numpy as np
from collections import Counter
import rhinoMorph
import  cx_Oracle
delay=3
browser = Chrome()
browser.implicitly_wait(delay)

print("== 유튜브 강의추천 알고리즘 시스템 계정 화면입니다 ==")
keyword = input("강의 검색어를 입력하세요 :")
#keyword='영어문법'

start_url = 'https://www.youtube.com/results?search_query='+keyword
browser.get(start_url)


body = browser.find_element_by_tag_name('body')
html0 = browser.page_source
html = BeautifulSoup(html0, 'html.parser')

# 조회순 검색
browser.find_element_by_xpath('//*[@id="container"]/ytd-toggle-button-renderer').click()
time.sleep(3)

#browser.find_element_by_xpath('//*[@id="endpoint"]').click()
browser.find_element_by_xpath('/html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[1]/div[2]/ytd-search-sub-menu-renderer/div[1]/iron-collapse/div/ytd-search-filter-group-renderer[5]/ytd-search-filter-renderer[3]/a').click()

time.sleep(2)

list_view_count=[]
like_count=[]
dislike_count=[]
video_name_list=[]
video_url_list=[]


csv_arr = ''
for ii in range(0,10,1):
    browser.back()
    time.sleep(1.5)

    #.find_elements_by_xpath('//*[@id="dismissable"]/div')[ii].click()
   # browser.find_element_by_css_selector('ytd-thumbnail #thumbnail.ytd-thumbnail')[ii].click()
    browser.find_elements_by_xpath('//*[@id="dismissable"]/ytd-thumbnail')[ii].click()
    time.sleep(3)
    try:
        video_url_list.append(browser.current_url)
        some_tag = browser.find_element_by_id('sections')
        action = ActionChains(browser)
        action.move_to_element(some_tag).perform()
        time.sleep(0.2)
    except:
        print('!!!!!!!!!!!!!!!오류발생 ==>')
        break


   # browser.find_element_by_xpath('//paper-button[@class="dropdown-trigger style-scope yt-dropdown-menu"]').click()
    browser.find_element_by_xpath('//*[@id="trigger"]').click()
    #browser.find_element_by_xpath('//paper-listbox[@class="dropdown-content style-scope yt-dropdown-menu"]/a[1]').click()
    time.sleep(0.1)
    browser.find_element_by_xpath('//*[@id="menu"]/a[1]/paper-item/paper-item-body/div[1]').click()

    body = browser.find_element_by_tag_name('body')

    num_page_down = 20
    while num_page_down:
        body.send_keys(Keys.PAGE_DOWN)
        time.sleep(0.6)
        num_page_down -= 1

    comment_data = pd.DataFrame({'youtube_id':[], 'comment':[], 'like_num':[]})
    html_s0 = browser.page_source
    html_s = BeautifulSoup(html_s0, 'html.parser')

    comment0 = html_s.find_all('ytd-comment-renderer', {'class': 'style-scope ytd-comment-thread-renderer'})

    youtube_user_IDs = html_s.select('div#header-author > a > span')
    youtube_comments = html_s.select('yt-formatted-string#content-text')
    youtube_count = html_s.select('span#vote-count-left')
    youtube = html_s.find('div',{'id':'menu'}).find_all('yt-formatted-string',{'id','text'})

    print('===========강의 조회수, 좋아요수, 싫어요수, =============')

    youtube_reader_count1 = html_s.find('div',{'class':'style-scope ytd-video-primary-info-renderer'})
    video_name_list.append(youtube_reader_count1.find('yt-formatted-string',{'class':'style-scope ytd-video-primary-info-renderer'}).text)
    youtube_reader_count2 = youtube_reader_count1.find('span',{'class':'view-count style-scope yt-view-count-renderer'}).text
    youtube_reader_count3 = youtube_reader_count1.find('div',{'class':'style-scope ytd-video-primary-info-renderer'})

    print('강의조회수 : ',youtube_reader_count2.split()[1][:-1])
    list_view_count.append(youtube_reader_count2.split()[1][:-1].replace(',',''))
    youtube1 = youtube_reader_count3.find_all('yt-formatted-string',{'class':'style-scope ytd-toggle-button-renderer style-text'})
    count=0
    for youtube2 in youtube1:
        if(count == 0):
            print("좋아요 : ",youtube2.get('aria-label').split()[1][:-1].replace(',',''))
            like_count.append(youtube2.get('aria-label').split()[1][:-1].replace(',',''))
        else:
            print("싫어요 : ", youtube2.get('aria-label').split()[-1])
            dislike_count.append(youtube2.get('aria-label').split()[1][:-1].replace(',',''))
        count = count+1


    str_youtube_userIDs = []
    str_youtube_comments = []
    str_youtube_count = []
    for i in range(len(youtube_user_IDs)):
        str_tmp = str(youtube_user_IDs[i].text)
    # print(str_tmp)
        str_tmp = str_tmp.replace('\n', '')
        str_tmp = str_tmp.replace('\t', '')
        str_tmp = str_tmp.replace(' ','')
        str_youtube_userIDs.append(str_tmp)

        str_tmp = str(youtube_comments[i].text)
        str_tmp = str_tmp.replace('\n', '')
        str_tmp = str_tmp.replace('\t', '')
        # str_tmp = str_tmp.replace(' ', '')
        str_youtube_comments.append(str_tmp)

        str_tmp = str(youtube_count[i].text)
        str_tmp = str_tmp.replace('\n', '')
        str_tmp = str_tmp.replace('\t', '')
        str_tmp = str_tmp.replace(' ', '')
        str_youtube_count.append(str_tmp)

    pd_data = {"ID":str_youtube_userIDs, "Comment":str_youtube_comments, "Count":str_youtube_count}
    csv_arr = './data/youtube_pd'+str(ii)+'.csv'
    print(csv_arr)
    pd.DataFrame(pd_data, columns=['ID','Comment','Count']).to_csv(csv_arr)

    comment_data = pd.read_csv(csv_arr)
    comment_list = []
    for i in range(len(comment_data)):
        comment_list.append(comment_data['Comment'].iloc[i])

    print(comment_list)



print(list_view_count)


comment_pd_data = {"name" : video_name_list,
                   "url" : video_url_list,
                    "viewCount": list_view_count,
                   "likeCount": like_count,
                   "dislikeCount": dislike_count }
statics_pd = pd.DataFrame(comment_pd_data,
                 columns=['name','url','viewCount', 'likeCount', 'dislikeCount'])





'''part II'''

def read_data(filename, encoding='utf-8'):
    with open(filename, 'r' ,encoding=encoding) as f:
        data = [line.split('\t') for line in f.read().splitlines()]
        data = data[1:]
    return data

def write_data(data,filename,encoding='utf-8'):
    with open(filename, 'w', encoding=encoding) as f:
        f.write(data)

def cntWordInLine(data, senti):  #명사분석, 긍정/부정
    senti_found = []
    for onedata in data:
        oneline_word = onedata.split(' ')  # 한 줄의 데이터를 공백 단위로 분리하여 리스트로 저장
        senti_temp = 0
        for sentiword in senti:
            if sentiword[0] in oneline_word:  # posword[0] 하여 리스트를 문자열로 추출
                senti_temp += 1  # 현재의 감정단어와 일치하면 숫자를 하나 올려 줌 (중복은 계산 안 함)
        senti_found.append(senti_temp)  # 현재의 줄에서 찾은 감성단어의 숫자를 해당 위치에 저장
    return senti_found

rn = rhinoMorph.startRhino()

positive = read_data('./dictionary/pos_pol_word.txt')
nggative = read_data('./dictionary/neg_pol_word.txt')


arr_pos = []
arr_neg = []
for ii in range(0,10):
    morphed_data = ''
    name = './data/youtube_pd'+str(ii)+'.csv'
    comment_data = pd.read_csv(name)

    for i in range(len(comment_data)):
        morphed_data_each = rhinoMorph.onlyMorph_list(rn, comment_data['Comment'][i],
                                                      pos=['NNG', 'NNP', 'VV', 'VA', 'XR', 'IC', 'MM', 'MAG', 'MAJ'],                                                  eomi=True)
        if(len(morphed_data_each)==0):
            morphed_data_each='가'
        joined_data_each = ' '.join(morphed_data_each)  # 문자열을 하나로 연결
        if joined_data_each:
            if(len(comment_data)-1 == i):
                morphed_data = morphed_data + joined_data_each
            else:
                morphed_data = morphed_data + joined_data_each + ' '
    '''f = open('.data/temp.txt', 'w', encoding='utf-8')
    f.write(morphed_data)
    f.close()
    
    f = open('.data/temp.txt', 'r', encoding='utf-8')
    data = f.read()
    f.close()'''

    mergedText = ''.join(morphed_data)  # 좋은 방법. 공백을 추가하며 모든 리스트 요소들을 결합한다
    #print('mergedText:', mergedText)

    mergedTextList = mergedText.split(' ')  # 결합된 요소들을 공백 단위로 분리하여 하나의 리스트로 만든다
    print('======',ii,'번쨰====')
    print('mergedTextList:', mergedTextList)

    wordInfo = Counter(mergedTextList)  # 하나의 리스트로 결합된 요소를 카운트한다 (내림차순)
    print('wordInfo:', wordInfo)


    data_senti_poscnt = cntWordInLine(mergedTextList, positive)  # 발견된 긍정 단어의 숫자 파악
    data_senti_negcnt = cntWordInLine(mergedTextList, nggative)  # 발견된 부정 단어의 숫자 파악

    print(data_senti_poscnt)
    print(data_senti_negcnt)

    pos_sum=0
    neg_sum=0
    list = [data_senti_poscnt,data_senti_negcnt]
    for i in range(0,2):
        for j in range(0,len(data_senti_poscnt),1):
            if(i==0):
                pos_sum=pos_sum+list[0][j]
            else:
                neg_sum=neg_sum+list[1][j]
    arr_pos.append(pos_sum)
    arr_neg.append(neg_sum)
    print('긍정댓글 : ',pos_sum, '|   부정댓글 :' , neg_sum)


print("긍정감정사전분석 : ",arr_pos)
print("부정감정사전분석 : ",arr_neg)
statics_pd['commentLikeCount'] = arr_pos
statics_pd['commentDislikeCount'] = arr_neg
statics_pd['score'] = statics_pd['viewCount'].astype(float) * 0.15 + statics_pd['likeCount'].astype(float) * 0.2+ statics_pd['dislikeCount'].astype(float) * 0.15 + statics_pd['commentLikeCount'].astype(float) * 0.25 + statics_pd['commentDislikeCount'].astype(float) * 0.25

statics_pd.to_csv('zcaculate.csv')
statics_pd.to_excel('zcaculate.xlsx')

#aa=statics_pd.sort_values(by=['score'], ascending=[False]).head(5)
aa=statics_pd.sort_values(by=['score'], ascending=[False])

print("===강의 순위대로 리스트업===")
print(aa)
records = statics_pd.to_records(index=False)

ii=[]
jj=[]

result1=0
try:
    conn = cx_Oracle.connect("lectureRecomand/lectureRecomand@localhost:1521/orcl")
    cursor = conn.cursor()
    cursor.execute("SELECT SEQ_TEST1.NEXTVAL from dual")
    result2 = cursor.fetchone()
    result1=result2[0]

except ZeroDivisionError as e:
    print(e)

t1=(result1,keyword)
jj.append(t1)
print("키워드 DB에 저장 : ",jj)
for record in records:
    aaee=(keyword , record[0] , record[1], record[7], result1 ,'Y')
    print(aaee)
    result1=result1+1
    ii.append(aaee)

time.sleep(3)

row=[]

def db():


    try:
        conn = cx_Oracle.connect("lectureRecomand/lectureRecomand@localhost:1521/orcl")
        #items = [('a','a','a','a','a','a')]
        #rows = [(1, 'Bob', 35, 'I like dogs'), (2, 'Kim', 27, 'I like birds')]
        cursor = conn.cursor()
        cursor.executemany("INSERT INTO LECTUREKEYWORD(ID,LECTURE_KEYWORD) VALUES (:0,:1 )",jj)
        cursor.executemany("INSERT INTO LECTURE(LECTURE_KEYWORD,LECTURE_NAME,LECUTRN_LINK,LECTURE_RANK,ID ,LECTURE_CANCEL_YN) VALUES (:0,:1, :2, :3, :4, :5 )", ii)
        cursor.close()
        conn.commit()
        conn.close()
    except ZeroDivisionError as e:
        print(e)





db()