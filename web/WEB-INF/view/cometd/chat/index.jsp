<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>CometD chat</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/chat.css"/>">
    <script type="text/javascript">
        var config = {
            //contextPath: (new String(document.location).replace(/http:\/\/[^\/]*/, '').replace(/\/cometd-spring-pilot\/.*$/, ''))
            contextPath: '/prototype/'
        };
    </script>
</head>

<body>

<h1>CometD Chat Example</h1>
<h6> <a href="../..">Main Demo index</a> </h6>

<div id="chatroom">
    <div id="chat"></div>
    <div id="members"></div>
    <div id="input">
        <div id="join">
            <table>
                <tbody>
                <tr>
                    <td>
                        <input id="useServer" type="checkbox" />
                    </td>
                    <td>
                        <label for="useServer" title="Selects whether to use cross-domain CometD">Use Alternate Server</label>
                    </td>
                    <td>
                        <input id="altServer" type="text" value="http://127.0.0.1:8080/cometd/cometd" />
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        Enter Chat Nickname
                    </td>
                    <td>
                        <input id="username" type="text" />
                    </td>
                    <td>
                        <button id="joinButton" class="button">Join</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div id="joined">
            Chat:
            &nbsp;
            <input id="phrase" type="text" />
            <button id="sendButton" class="button">Send</button>
            <button id="leaveButton" class="button">Leave</button>
        </div>
    </div>
</div>
<br />
<div style="padding: 0.25em;">Tip: Use username[,username2]::text to send private messages</div>

<script type="text/javascript" data-main="<c:url value="/js/chat"/>" src="<c:url value="/js/require-jquery.js"/>"></script>
</body>

</html>
