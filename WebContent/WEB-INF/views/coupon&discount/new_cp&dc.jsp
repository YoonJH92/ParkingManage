<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>쿠폰 생성</title>
    <script src="./resources/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="./resources/bootstrap.min.css" />
    <script>
        $(function () {

        });
    </script>
</head>

<body>
    <div>쿠폰 할인권 생성</div>
    <table border="1">
        <th>쿠폰 할인권 선택</th>
        <th>쿠폰 <input id="coupon" type="radio" name="c_d" value="쿠폰" checked /> 할인권 <input id="discount" type="radio"
                name="c_d" value="할인권" />
    </table>
    <div id="toggle">
        <table border="1">
            <tr>
                <td>사용 기간</td>
                <td>발급일로부터
                    <select name="date">
                        <option value="1">1</option>
                        <option value="7">7</option>
                        <option value="10">10</option>
                        <option value="30">30</option>
                        <option value="100">100</option>
                        <option value="직접입력">직접 입력</option>
                    </select>
                    일
                    <input type="text" name="date">일
                </td>
            </tr>
            <tr>
                <td>사용 목적</td>
                <td>
                    <p>첫 가입을 축하하기 위해 발급</p>
                </td>
            </tr>
            <tr>
                <td>차감 금액</td>
                <td><input type="text" name="" />
                    <select name="date">
                        <option value="1000">1000</option>
                        <option value="3000">3000</option>
                        <option value="5000">5000</option>
                        <option value="10000">10000</option>
                        <option value="30000">30000</option>
                        <option value="직접입력">직접 입력</option>
                    </select>
                    원
                    <input type="text" name="date">일
                </td>
            </tr>
        </table>
    </div>
</body>

</html>