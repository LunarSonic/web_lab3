import React, {useState} from "react";
import {Table} from "../table/Table";
import {Canvas} from "../canvas/Canvas";
import {useAddPointMutation, useGetPointsQuery} from "../../store/api/pointsApi";
import {useForm} from "react-hook-form";
import {useErrorVisibility} from "../../hooks/useErrorVisibility";
import {ServerErrorMessage} from "../errorMessage/ServerErrorMessage";
import {XField} from "../formFields/XField";
import {YField} from "../formFields/YField";
import {RField} from "../formFields/RField";
import "../../styles/style.css";

export function MainForm() {
    const xItems = [
        { label: '-2', value: -2.0 },
        { label: '-1.5', value: -1.5 },
        { label: '-1', value: -1.0 },
        { label: '-0.5', value: -0.5 },
        { label: '0', value: 0.0 },
        { label: '0.5', value: 0.5 },
        { label: '1', value: 1.0 },
        { label: '1.5', value: 1.5 },
        { label: '2', value: 2.0 }
    ];

    const rItems = [
        { label: '-2', value: -2.0 },
        { label: '-1.5', value: -1.5 },
        { label: '-1', value: -1.0 },
        { label: '-0.5', value: -0.5 },
        { label: '0', value: 0.0 },
        { label: '0.5', value: 0.5 },
        { label: '1', value: 1.0 },
        { label: '1.5', value: 1.5 },
        { label: '2', value: 2.0 }
    ];

    const [addPoint] = useAddPointMutation();
    const {data: points} = useGetPointsQuery();
    const [yDisplay, setYDisplay] = useState("0");

    const {handleSubmit, control, reset, watch, formState: {errors}} = useForm({defaultValues: {x: null, y: 0, r: null}});

    const xShowError = useErrorVisibility(errors.x);
    const yShowError = useErrorVisibility(errors.y);
    const rShowError = useErrorVisibility(errors.r);

    const canvasHandlers = {addPoint};
    const [errorMessage, setErrorMessage] = useState("");

    const onSubmit = async (data) => {
        setErrorMessage("")
        try {
            await addPoint(data).unwrap();
        } catch (error) {
            if (error && error.data && error.data.message) {
                setErrorMessage(error.data.message);
            }
        }
    }

    const handleClearForm = () => {
        reset();
        setYDisplay("0");
    }

    return (
        <div id="canvas_container">
            <div id="left_column">
                <form id="main_form" onSubmit={handleSubmit(onSubmit)}>
                    <XField
                        control={control}
                        xShowError={xShowError}
                        errors={errors}
                        xItems={xItems}
                    />
                    <YField
                        control={control}
                        errors={errors}
                        yDisplay={yDisplay}
                        setYDisplay={setYDisplay}
                        yShowError={yShowError}
                    />
                    <RField
                        control={control}
                        rShowError={rShowError}
                        errors={errors}
                        rItems={rItems}
                    />
                    <div id="button_container">
                        <button id="submit_button" type="submit">Подтвердить</button>
                        <button id="clear_form_button" type="button" onClick={handleClearForm}>Очистить форму</button>
                    </div>
                    <ServerErrorMessage errorMessage={errorMessage} />
                </form>
                <h3 id="history_header">История проверок</h3>
                <Table points={points || []}/>
            </div>
            <Canvas r={watch("r")} points={points || []} formHandlers={canvasHandlers}/>
        </div>
    );
}