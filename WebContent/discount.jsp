<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="table table-bordered">
            <tr>
                <td>발급처</td>
                <td><input type="text" name="company"/>
                </td>
            </tr>
            <tr>
                <td>발급 목적</td>
                <td>
                    <input type="text" name="purpose"/>
                </td>
            </tr>
            <tr>
                <td>할인 시간</td>
                <td>
                    <span id="show2"></span>
                    <select name="time">
                        <option value="1" selected>1시간</option>
                        <option value="3">3시간</option>
                        <option value="6">6시간</option>
                        <option value="10">10시간</option>
                        <option value="24">24시간</option>
                        <option value="직접 입력">직접 입력</option>
                    </select>
                    <span id="time"><input type="text" name="time" numberOnly>시간</span>
                </td>
            </tr>
        </table>