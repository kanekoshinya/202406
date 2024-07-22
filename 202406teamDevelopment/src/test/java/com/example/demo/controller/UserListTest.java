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
class UserListControllerTest {

 //Serviceはチェックしないため事前にモック化しておく
 @MockBean
 UserListService userlistService;

 //URLリクエストをJunitで行う場合に必要なモックを準備する
 @Autowired
 private MockMvc mockMvc;

 /**
  * 【正常系】GETリクエストが正常に処理され、ユーザー一覧画面が表示されListがレスポンスとして正しく返ってきていることを検証する
  * レスポンスとして0件のデータが返ってきている
  * @throws Exception
  */
 @Test
 public void Test01() throws Exception {
  //事前にServiceのsearchAllメソッドを呼び出された際のレスポンス形式を作成する
  List<UserListEntity> userlist = new ArrayList<UserListEntity>();
  UserListEntity userlistEntity = new UserListEntity();
  //レスポンス予定の値を設定しリストに格納する
  userlistEntity.setUser_id(null);
  userlistEntity.setName(null);
  userlistEntity.setFurigana(null);
  userlistEntity.setMail(null);
  userlistEntity.setPassword(null);
  
  //searchAll()メソッドが呼び出された際の返却値を設定する
  when(userlistService.searchAll()).thenReturn(userlist);
  
//実際に下記のURLにリクエスト送る「ステータス・HTML返却変数・レスポンスビュー名」が期待値通りか確認する
 mockMvc.perform(get("/userList"))
   //返却HTTPステータスが200(正常)である事の確認
   .andExpect(status().isOk())
   //画面へ返却するレスポンスリストとして正常である事の確認
   .andExpect(model().attribute("userlist", userlist))
   //リクエストを呼ばれた際のHTMLファイル名が正常である事の確認
   .andExpect(view().name("userList"));
 
 //searchAll()メソッドを呼び出した際のリストの格納件数をcountに代入する
 int count = userlistService.searchAll().size();
 //searchAll()メソッドを呼び出した際のリストの格納件数が期待通りか確認する
 assertEquals(0, count);
 
 //今回のsearchAll()メソッドがmockMvcでの呼び出しと直接searchAll()を呼び出しているため正常に2回、メソッドが呼び出されているか確認
 Mockito.verify(userlistService, times(2)).searchAll();
 }

 /**
  * 【正常系】GETリクエストが正常に処理され、ユーザー一覧画面が表示されListがレスポンスとして正しく返ってきていることを検証する
  * レスポンスとして1件のデータが返ってきている
  * @throws Exception
  */
 
 @Test
 public void Test02() throws Exception {
//事前にServiceのsearchAllメソッドを呼び出された際のレスポンス形式を作成する
List<UserListEntity> userlist = new ArrayList<UserListEntity>();
UserListEntity userlistEntity = new UserListEntity();
//レスポンス予定の値を設定しリストに格納する
userlistEntity.setUser_id(1);
userlistEntity.setName("山田太郎");
userlistEntity.setFurigana("ヤマダタロウ");
userlistEntity.setMail("yamada.tarou.12345@gmail.com");
userlistEntity.setPassword("tarotaro");
userlist.add(userlistEntity);
//searchAll()メソッドが呼び出された際の返却値を設定する
when(userlistService.searchAll()).thenReturn(userlist);
	  
//実際に下記のURLにリクエスト送る「ステータス・HTML返却変数・レスポンスビュー名」が期待値通りか確認する
 mockMvc.perform(get("/userList"))
   //返却HTTPステータスが200(正常)である事の確認
   .andExpect(status().isOk())
   //画面へ返却するレスポンスリストとして正常である事の確認
   .andExpect(model().attribute("userlist", userlist))
   //リクエストを呼ばれた際のHTMLファイル名が正常である事の確認
   .andExpect(view().name("userList"));
 
 //searchAll()メソッドを呼び出した際のリストの格納件数をcountに代入する
 int count = userlistService.searchAll().size();
 //searchAll()メソッドを呼び出した際のリストの格納件数が期待通りか確認する
 assertEquals(1, count);
 
 //今回のsearchAll()メソッドがmockMvcでの呼び出しと直接searchAll()を呼び出しているため正常に2回、メソッドが呼び出されているか確認
 Mockito.verify(userlistService, times(2)).searchAll();
 }
 
 /**
  * 【正常系】GETリクエストが正常に処理され、ユーザー一覧画面が表示されListがレスポンスとして正しく返ってきていることを検証する
  * レスポンスとして2件のデータが返ってきている
  * @throws Exception
  */
 
 @Test
 public void Test03() throws Exception {
	 
//事前にServiceのsearchAllメソッドを呼び出された際のレスポンス形式を作成する
List<UserListEntity> userlist = new ArrayList<UserListEntity>();
UserListEntity userlistEntity = new UserListEntity();
//レスポンス予定の値を設定しリストに格納する
userlistEntity.setUser_id(1);
userlistEntity.setName("山田太郎");
userlistEntity.setFurigana("ヤマダタロウ");
userlistEntity.setMail("yamada.tarou.12345@gmail.com");
userlistEntity.setPassword(null);
userlist.add(userlistEntity);
	  
userlistEntity.setUser_id(2);
userlistEntity.setName("鈴木花子");
userlistEntity.setFurigana("スズキハナコ");
userlistEntity.setMail("suzuki.hanako.67890@gmail.com");
userlistEntity.setPassword("");

userlist.add(userlistEntity);
//searchAll()メソッドが呼び出された際の返却値を設定する
when(userlistService.searchAll()).thenReturn(userlist);
	  
  
//実際に下記のURLにリクエスト送る「ステータス・HTML返却変数・レスポンスビュー名」が期待値通りか確認する
 mockMvc.perform(get("/userList"))
   //返却HTTPステータスが200(正常)である事の確認
   .andExpect(status().isOk())
   //画面へ返却するレスポンスリストとして正常である事の確認
   .andExpect(model().attribute("userlist",userlist))
   //リクエストを呼ばれた際のHTMLファイル名が正常である事の確認
   .andExpect(view().name("userList"));
 
 //searchAll()メソッドを呼び出した際のリストの格納件数をcountに代入する
 int count = userlistService.searchAll().size();
 //searchAll()メソッドを呼び出した際のリストの格納件数が期待通りか確認する
 assertEquals(2, count);
 
 //今回のsearchAll()メソッドがmockMvcでの呼び出しと直接searchAll()を呼び出しているため正常に2回、メソッドが呼び出されているか確認
 Mockito.verify(userlistService, times(2)).searchAll();
 }

}