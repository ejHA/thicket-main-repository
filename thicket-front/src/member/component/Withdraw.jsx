import React, { useState } from "react";
import "../../assets/css/setting/withdraw.css";
import "../../assets/css/setting/fonts.css";
import "../../assets/css/setting/mixin.css";

const Withdraw = () => {
  const [password, setPassword] = useState('');
  const [showPasswordInput, setShowPasswordInput] = useState(false);

  const handleWithdrawal = () => {
    if (showPasswordInput) {
      if (password.trim() === '') {
        alert("본인 확인용 비밀번호를 입력해주십시오.");
        return;
      }
      fetch('/members',{
        method: "DELETE",
        headers: {
          'Email':'test123@gmail.com',
          'Content-Type':'application/json'
        },
        body: JSON.stringify({password})
      })
      .then(res => res.text())
      .then(data => alert(data))
    } else {
      // "탈퇴하기" 버튼을 클릭하면 비밀번호 입력란을 보여줌
      setShowPasswordInput(true);
    }
  };
  
  return (
    <section id="withdraw" className="withdraw">
      <div>
        <h1 className="withdraw_title"><b> 탈퇴하기 </b></h1>
        <hr />
        <br />

        <p>탈퇴 시 해당 계정 내 모든 예매 정보는 확인 불가합니다.</p>
        <br />
        <p>회원님의 개인정보는 탈퇴 후 2년간 보관되며, </ p>
        <p>  동일 이메일로 재가입하실 경우 개인정보 인증이 필요합니다. </p>
        <br /> <br />
        <h3 style={{ color: "red" }}>정말 탈퇴하시겠습니까?</h3>

        <br />

        {showPasswordInput && (
          <>
            <label htmlFor="password" style={{ fontSize: "18px", margin: "4px", color: "inherit" }}>
              비밀번호:</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => {
                setPassword(e.target.value);
              }}
              placeholder="비밀번호를 입력하세요"
              style={{
                border: "2px solid gray", borderRadius: "5px",
                padding: "5px", width: "200px",
              }}
            />
            <br />
          </>
        )}
        <br />
        <button className="withdrawal-button" onClick={handleWithdrawal} >
          {showPasswordInput ? '탈퇴하기' : '비밀번호 확인'}
        </button>
      </div>
    </section>
  );
};

export default Withdraw;