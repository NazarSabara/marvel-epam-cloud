import React, {useState} from 'react';
import HeroList from './HeroList/HeroList';
import {Form, RadioButtonGroup, Button} from 'grommet';
import './BattlePage.scss';

function BattlePage() {

    // TODO: replace with BE call
    const heroes = [
        { value: 'Batman', text: 'Batman' },
        { value: 'Spiderman', text: 'Spiderman' }
    ];

    const battleTypeOptions = ['1 vs 1', '3 vs 3']
    const [battleType, setBattleType] = useState('1 vs 1');
    const [selectedFirst, setSelectedFirst] = useState([]);
    const [selectedSecond, setSelectedSecond] = useState([]);

    const handleChangeFirst = (value) => {
        setSelectedFirst(value);
    
    }
    const handleChangeSecond = (value) => {
        setSelectedSecond(value);
    }

    return (
        <Form class='battlePageElement'>
            <RadioButtonGroup
                name="battleType"
                options={battleTypeOptions}
                value={battleType}
                onChange={(event) => setBattleType(event.target.value)}
            />
            <Form class='listForm'>
                <HeroList isTeamBattle={battleType == battleTypeOptions[1]} heroes={heroes.filter(hero => !selectedSecond.includes(hero.value))} onChange={handleChangeFirst}></HeroList>
                <HeroList isTeamBattle={battleType == battleTypeOptions[1]} heroes={heroes.filter(hero => !selectedFirst.includes(hero.value))} onChange={handleChangeSecond}></HeroList>
            </Form>
            <Button type="button" primary label="Battle" size="medium" margin="small"/>
        </Form>
    );
}

export default BattlePage;