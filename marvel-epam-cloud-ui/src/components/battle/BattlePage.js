import React, {useState} from 'react';
import HeroList from './HeroList/HeroList';

function BattlePage() {

    // TODO: replace with BE call
    const heroes = [
        { value: 'Batman', text: 'Batman' },
        { value: 'Spiderman', text: 'Spiderman' }
    ];
    
    const [selectedFirst, setSelectedFirst] = useState([]);
    const [selectedSecond, setSelectedSecond] = useState([]);

    const handleChangeFirst = (value) => {
        setSelectedFirst(value);
    }
    const handleChangeSecond = (value) => {
        setSelectedSecond(value);
    }

    const isTvTBattle = true;

    return (
        <>
            <HeroList multiple={isTvTBattle} heroes={heroes.filter(x => !selectedSecond.includes(x.value))} onChange={handleChangeFirst}></HeroList>
            <HeroList multiple={isTvTBattle} heroes={heroes.filter(x => !selectedFirst.includes(x.value))} onChange={handleChangeSecond}></HeroList>
        </>
    );
}

export default BattlePage;