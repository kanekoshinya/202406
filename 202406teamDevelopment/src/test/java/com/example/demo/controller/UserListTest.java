package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.UserListEntity;
import com.example.demo.service.UserListService;

@WebMvcTest(UserListController.class)
class UserListTest {

 //Serviceはチェックしないため事前にモック化しておく
 @MockBean
 UserListService userlistService;

 //URLリクエストをJunitで行う場合に必要なモックを準備する
 @Autowired
 private MockMvc mockMvc;

 /**
  * 【正常系】GETリクエストが正常に処理され、科目一覧画面が表示されListがレスポンスとして正しく返ってきていることを検証する
  * レスポンスとして0件のデータが返ってきている
  * @throws Exception
  */
 @Test
 public void Test01() throws Exception {
  //事前にServiceのsearchAllメソッドを呼び出された際のレスポンス形式を作成する
  List<UserListEntity> userlist = new ArrayList<UserListEntity>();
  UserListEntity userEntity = new UserListEntity();
  //レスポンス予定の値を設定しリストに格納する
  userEntity.setUser_id(0);
  userEntity.setName(null);
  userEntity.setFurigana(null);
  userEntity.setMail(null);
  userEntity.setPassword(null);
  userlist.add(userEntity);
  //searchAllメソッドが呼び出された際の返却値を設定する
  when(userlistService.searchAll()).thenReturn(userlist);
 
//実際に下記のURLにリクエスト送る「ステータス・HTML返却変数・レスポンスビュー名」が期待値通りか確認する
 mockMvc.perform(get("/UserList"))
   //返却HTTPステータスが200(正常)である事の確認
   .andExpect(status().isOk())
   //画面へ返却するレスポンスリストとして正常である事の確認
   .andExpect(model().attribute("userlist", userlist))
   //リクエストを呼ばれた際のHTMLファイル名が正常である事の確認
   .andExpect(view().name("UserList"));
 
 //searchAllメソッドを呼び出した際のリストの格納件数をcountに代入する
 int count = userlistService.searchAll().size();
 //searchAllメソッドを呼び出した際のリストの格納件数が期待通りか確認する
 assertEquals(0, count);
 
 //今回のsearchAllメソッドがmockMvcでの呼び出しと直接searchAllを呼び出しているため正常に2回、メソッドが呼び出されているか確認
 Mockito.verify(userlistService, times(2)).searchAll();
}
 
 /**
  * 【正常系】GETリクエストが正常に処理され、科目一覧画面が表示されListがレスポンスとして正しく返ってきていることを検証する
  * レスポンスとして1件のデータが返ってきている
  * @throws Exception
  */
 
 @Test
 public void Test02() throws Exception {
  //事前にServiceのsearchAllメソッドを呼び出された際のレスポンス形式を作成する
  List<UserListEntity> userlist = new ArrayList<UserListEntity>();
  UserListEntity userEntity = new UserListEntity();
  //レスポンス予定の値を設定しリストに格納する
  userEntity.setUser_id(1);
  userEntity.setName("山田太郎");
  userEntity.setFurigana("ヤマダタロウ");
  userEntity.setMail("yamada.tarou.12345@gmail.com");
  userEntity.setPassword("tarotaro");
  userlist.add(userEntity);
  //searchAllメソッドが呼び出された際の返却値を設定する
  when(userlistService.searchAll()).thenReturn(userlist);
  
//実際に下記のURLにリクエスト送る「ステータス・HTML返却変数・レスポンスビュー名」が期待値通りか確認する
 mockMvc.perform(get("/UserList"))
   //返却HTTPステータスが200(正常)である事の確認
   .andExpect(status().isOk())
   //画面へ返却するレスポンスリストとして正常である事の確認
   .andExpect(model().attribute("userlist", userlist))
   //リクエストを呼ばれた際のHTMLファイル名が正常である事の確認
   .andExpect(view().name("UserList"));
 
 //searchAllメソッドを呼び出した際のリストの格納件数をcountに代入する
 int count = userlistService.searchAll().size();
 //searchAllメソッドを呼び出した際のリストの格納件数が期待通りか確認する
 assertEquals(1, count);
 
 //今回のsearchAllメソッドがmockMvcでの呼び出しと直接searchAllを呼び出しているため正常に2回、メソッドが呼び出されているか確認
 Mockito.verify(userlistService, times(2)).searchAll();
 }
 
 /**
  * 【正常系】GETリクエストが正常に処理され、科目一覧画面が表示されListがレスポンスとして正しく返ってきていることを検証する
  * レスポンスとして2件のデータが返ってきている
  * @throws Exception
  */
 
 @Test
 public void Test03() throws Exception {
  //事前にServiceのsearchAllメソッドを呼び出された際のレスポンス形式を作成する
  List<UserListEntity> userlist = new ArrayList<UserListEntity>();
  UserListEntity userEntity = new UserListEntity();
  //レスポンス予定の値を設定しリストに格納する
  userEntity.setUser_id(1);
  userEntity.setName("山田太郎");
  userEntity.setFurigana("ヤマダタロウ");
  userEntity.setMail("yamada.tarou.12345@gmail.com");
  userEntity.setPassword("tarotaro");
  userlist.add(userEntity);
  
  userEntity.setUser_id(2);
  userEntity.setName("鈴木花子");
  userEntity.setFurigana("スズキハナコ");
  userEntity.setMail("suzuki.hanako.67890@gmail.com");
  userEntity.setPassword("hanahana");
  userlist.add(userEntity);
  //searchAllメソッドが呼び出された際の返却値を設定する
  when(userlistService.searchAll()).thenReturn(userlist);
  
//実際に下記のURLにリクエスト送る「ステータス・HTML返却変数・レスポンスビュー名」が期待値通りか確認する
 mockMvc.perform(get("/UserList"))
   //返却HTTPステータスが200(正常)である事の確認
   .andExpect(status().isOk())
   //画面へ返却するレスポンスリストとして正常である事の確認
   .andExpect(model().attribute("userlist", userlist))
   //リクエストを呼ばれた際のHTMLファイル名が正常である事の確認
   .andExpect(view().name("UserList"));
 
 //searchAllメソッドを呼び出した際のリストの格納件数をcountに代入する
 int count = userlistService.searchAll().size();
 //searchAllメソッドを呼び出した際のリストの格納件数が期待通りか確認する
 assertEquals(1, count);
 
 //今回のsearchAllメソッドがmockMvcでの呼び出しと直接searchAllを呼び出しているため正常に2回、メソッドが呼び出されているか確認
 Mockito.verify(userlistService, times(2)).searchAll();
 }
}