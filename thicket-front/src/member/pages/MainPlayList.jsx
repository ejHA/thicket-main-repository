import {
    Wrapper,
    InvisibleScroll,
    MainContainer,
    H1,
    DivList1, Poster1, Img1, ImgInfo1,
} from "../../assets/css/setting/MainStyleCSS";
import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";

const ShowList = () => {
    const [shows, setShows] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/shows/stagetype/PLAY')
            .then(response => response.json())
            .then(data => {
                setShows(data);
            });
    }, []);

    const formatDateString = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString(); // Adjust the format as needed
    };

    return (
        <DivList1>
            {Array.isArray(shows) ? (
                shows.map(show => (
                    <Poster1 key={show.id} onClick={() => navigate(`/detail/${show.id}`)}>
                        <Img1 src={show.posterImg} alt="Poster" />
                        <ImgInfo1>
                            <div>{show.name}</div>
                            <div>{show.place}</div>
                            <div>{`${formatDateString(show.stageOpen)} ~ ${formatDateString(show.stageClose)}`}</div>
                        </ImgInfo1>
                    </Poster1>
                ))
            ) : (
                <H1>없습니다.　　　　　　　　아니 그냥 없어요.</H1>
            )}
        </DivList1>
    );
};

function MainPlayList() {

    return (
        <Wrapper>
            <InvisibleScroll>
                <MainContainer>
                    <H1>진행중인 연극 목록</H1>
                    <ShowList />
                </MainContainer>
            </InvisibleScroll>
        </Wrapper>
    );
};

export default MainPlayList;