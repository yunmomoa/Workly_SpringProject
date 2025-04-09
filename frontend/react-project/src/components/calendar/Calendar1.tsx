import { useEffect, useState, useRef } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import FullCalendar from "@fullcalendar/react";
import { EventClickArg, EventInput } from "@fullcalendar/core";
import dayGridPlugin from "@fullcalendar/daygrid";
import allLocales from "@fullcalendar/core/locales-all";
import interactionPlugin from "@fullcalendar/interaction";
import styles from "./Calendar1.module.css";

interface Calendar1Props {
  events: EventInput[];
  setEvents: React.Dispatch<React.SetStateAction<EventInput[]>>;
  setSelectedEvent: (event: EventInput | null) => void;
  setModalOpen: (open: boolean) => void;
}

function Calendar1({ events, setEvents, setSelectedEvent, setModalOpen }: Calendar1Props) {
  const user = useSelector((state: any) => state.user);
  const userNo = user?.userNo;
  const calendarRef = useRef<FullCalendar | null>(null);
  const [calendarTitle, setCalendarTitle] = useState("");

  useEffect(() => {
    if (userNo) {
      axios
        .get(`http://localhost:8003/workly/schedule/user/${userNo}`)
        .then((response) => {
          console.log("📌 [내 일정] 백엔드에서 가져온 데이터:", response.data);
          const formattedEvents = response.data.map((event: any) => ({
            id: event.calNo,
            title: event.title,
            start: event.startDate,
            end: event.endDate,
            content: event.content || "",
            backgroundColor: event.color || "#000000",
            borderColor: event.color || "#000000",
          }));
          setEvents(prevEvents => JSON.stringify(prevEvents) !== JSON.stringify(formattedEvents) ? formattedEvents : prevEvents);
        })
        .catch((error) => console.error("내 일정 불러오기 오류:", error));
    }
  }, [userNo, events]);

  const handleEventClick = (clickInfo: EventClickArg) => {
    setSelectedEvent({
      id: clickInfo.event.id,
      title: clickInfo.event.title,
      start: clickInfo.event.startStr,
      end: clickInfo.event.endStr || clickInfo.event.startStr,
      content: clickInfo.event.extendedProps.content || "",
      backgroundColor: clickInfo.event.backgroundColor,
      type: clickInfo.event.extendedProps.category === "T" ? "팀 일정" : "내 일정", // ✅ 일정 유형 추가
    });
    setModalOpen(true);
  };
  


  const handlePrev = () => {
    if (calendarRef.current) {
      const calendarApi = calendarRef.current.getApi?.();
      if (calendarApi) {
        calendarApi.prev();
        setCalendarTitle(calendarApi.view.title);
      }
    }
  };

  const handleNext = () => {
    if (calendarRef.current) {
      const calendarApi = calendarRef.current.getApi?.();
      if (calendarApi) {
        calendarApi.next();
        setCalendarTitle(calendarApi.view.title);
      }
    }
  };

  const handleToday = () => {
    if (calendarRef.current) {
      const calendarApi = calendarRef.current.getApi?.();
      if (calendarApi) {
        calendarApi.today();
        setCalendarTitle(calendarApi.view.title);
      }
    }
  };

  return (
    <div className={styles.calendarContainer}>
      {/* ✅ 커스텀 툴바 추가 */}
      <div className={styles.customToolbar}>
        <button className={styles.toolbarButton} onClick={handlePrev}>&lt;</button>
        <h3 className={styles.customTitle}>{calendarTitle}</h3>
        <button className={styles.toolbarButton} onClick={handleNext}>&gt;</button>
        <button className={styles.todayButton} onClick={handleToday}>오늘</button>
      </div>

      <FullCalendar
        ref={calendarRef}
        plugins={[dayGridPlugin, interactionPlugin]}
        initialView="dayGridMonth"
        editable={true}
        events={events}
        eventClick={handleEventClick}
        locales={allLocales}
        locale="ko"
        firstDay={0}
        height="auto"
        headerToolbar={{ left: "", center: "", right: "" }} // ✅ 기본 툴바 제거
        datesSet={(info) => setCalendarTitle(info.view.title)}
      />
    </div>
  );
}

export default Calendar1;
