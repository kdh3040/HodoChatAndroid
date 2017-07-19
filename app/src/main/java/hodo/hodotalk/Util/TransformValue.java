package hodo.hodotalk.Util;

import hodo.hodotalk.Data.MyData;

/**
 * Created by boram on 2017-07-18.
 */

public class TransformValue {

    private static TransformValue _Instance;
    public static TransformValue getInstance()
    {
        if(_Instance == null)
            _Instance = new TransformValue();

        return  _Instance;
    }


    private TransformValue()
    {

    }

    public String Transform_Gender(int _Gender)
    {
        String rtValue = null;
        switch (_Gender) {
            case 0:
                rtValue = "여성";
                break;
            case 1:
                rtValue = "남성";
                break;
            default:
                rtValue = "--";
                break;
        }
        return  rtValue;
    }

    public String Transform_Age(int _Age)
    {
        String rtValue = null;
        switch (_Age)
        {
            case 0:
                rtValue= "10대";
                break;
            case 1:
                rtValue = "20대";
                break;
            case 2:
                rtValue ="30대";
                break;
            case 3:
                rtValue = "40대";
                break;
            default:
                rtValue = "--";
                break;

        }
        return rtValue;
    }

    public String Transform_Blood(int _Blood)
    {
        String rtValue = null;
        switch (_Blood)
        {
            case 0:
                rtValue= "A형";
                break;
            case 1:
                rtValue = "B형";
                break;
            case 2:
                rtValue ="O형";
                break;
            case 3:
                rtValue = "AB형";
                break;
            default:
                rtValue = "--";
                break;

        }
        return rtValue;
    }

    public String Transform_Loc(int _Loc)
    {
        String rtValue = null;
        switch (_Loc)
        {
            case 0:
                rtValue= "서울";
                break;
            case 1:
                rtValue = "경기도";
                break;
            case 2:
                rtValue ="부산";
                break;
            case 3:
                rtValue = "인천";
                break;
            case 4:
                rtValue = "경남";
                break;
            case 5:
                rtValue = "경북";
                break;
            case 6:
                rtValue = "대구";
                break;
            case 7:
                rtValue ="전북";
                break;
            case 8:
                rtValue = "전남";
                break;
            case 9:
                rtValue = "광주";
                break;
            case 10:
                rtValue = "대전";
                break;
            case 11:
                rtValue = "울산";
                break;
            case 12:
                rtValue ="강원";
                break;
            case 13:
                rtValue = "충북";
                break;
            case 14:
                rtValue = "충남";
                break;
            case 15:
                rtValue = "세종";
                break;
            case 16:
                rtValue = "제주";
                break;
            default:
                rtValue = "--";
                break;
        }
        return rtValue;
    }

    public String Transform_Rel(int _Rel)
    {
        String rtValue = null;
        switch (_Rel)
        {
            case 0:
                rtValue= "무교";
                break;
            case 1:
                rtValue = "불교";
                break;
            case 2:
                rtValue ="기독교";
                break;
            case 3:
                rtValue = "천주교";
                break;
            case 4:
                rtValue = "원불교";
                break;
            case 5:
                rtValue = "유교";
                break;
            case 6:
                rtValue = "이슬람";
                break;
            default:
                rtValue = "--";
                break;

        }
        return rtValue;
    }

    public String Transform_job(int _Job)
    {
        String rtValue = null;
        switch (_Job)
        {
            case 0:
                rtValue= "학생";
                break;
            case 1:
                rtValue = "교수";
                break;
            case 2:
                rtValue ="사장";
                break;
            case 3:
                rtValue = "회장";
                break;
            case 4:
                rtValue = "가수";
                break;
            case 5:
                rtValue = "트수";
                break;
            case 6:
                rtValue = "백수";
                break;
            case 7:
                rtValue = "공무원";
                break;
            default:
                rtValue = "--";
                break;

        }
        return rtValue;
    }

    public String Transform_Body(int _Body)
    {
        String rtValue = null;
        switch (_Body)
        {
            case 0:
                rtValue= "마른";
                break;
            case 1:
                rtValue = "슬림탄탄";
                break;
            case 2:
                rtValue ="보통";
                break;
            case 3:
                rtValue = "통통";
                break;
            case 4:
                rtValue = "근육";
                break;
            case 5:
                rtValue = "건장";
                break;
            default:
                rtValue = "--";
                break;
        }
        return rtValue;
    }


}
